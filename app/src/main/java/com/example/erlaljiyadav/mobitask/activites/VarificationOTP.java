package com.example.erlaljiyadav.mobitask.activites;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.configs.Configs;
import com.example.erlaljiyadav.mobitask.utility.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.erlaljiyadav.mobitask.configs.Configs.KEY_CUSTOMER_ID;
import static com.example.erlaljiyadav.mobitask.configs.Configs.KEY_OTP;

public class VarificationOTP extends AppCompatActivity {

    Toolbar toolbar;
    EditText edtOtp;
    TextView txtOTPConfirm, txt_timer, txt_resend;
    ImageView img_timer;
    ProgressDialog progressDialog;
    SharedPreferences signUpPref;
    private SharedPreferences myPrefsForSesssion;
    boolean rememberMe;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_otp);

        toolbar = (Toolbar) findViewById(R.id.toggle_toolbar);
        toolbar.setTitle("Verification");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        signUpPref = getSharedPreferences("SIGNUP_DETAILS", Context.MODE_PRIVATE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        edtOtp = findViewById(R.id.edt_otp);
        txtOTPConfirm = findViewById(R.id.txt_otp_confirm);
        txt_timer = findViewById(R.id.txt_timer);
        img_timer = findViewById(R.id.img_timer);
        txt_resend = findViewById(R.id.txt_resend);

        progressDialog = new ProgressDialog(this);

        progressDialog.setCancelable(true);
        progressDialog.setMessage("Verification.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (rememberMe) {
//            Intent i = new Intent(VarificationOTP.this, Home.class);
//            startActivity(i);
//            finish();
        }

        // This function for Timer

        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                txt_timer.setText("Waiting time: " + millisUntilFinished / 1000);

                //   progressDialog.show();
            }

            public void onFinish() {

                txt_timer.setText("Wait or Click on Resend OTP !");
                txt_resend.setVisibility(View.VISIBLE);
                txt_timer.setTextColor(getResources().getColor(R.color.light_blue));
                img_timer.setBackgroundResource(R.drawable.timer_white);
                //  txtOTPConfirm.setBackgroundResource(R.color.green);
                progressDialog.show();
            }
        }.start();
        txt_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        txtOTPConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOTP = edtOtp.getText().toString();
                otpVerification(SaveSharedPreference.getUserID(VarificationOTP.this), strOTP);
            }
        });

    }

    private void otpVerification(String userId, String otpNumber) {

        //  progressDialog.show();
        final String _userID = userId;
        final String _otpNumber = otpNumber;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configs.URL_OTP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObject = new JSONObject(response);
                    if (jObject.optString("error").equalsIgnoreCase("false")) {

                        SaveSharedPreference.setUserName(VarificationOTP.this,jObject.optString("name").toString());
                        SaveSharedPreference.setUserName(VarificationOTP.this,jObject.optString("email").toString());
                        SaveSharedPreference.setUserName(VarificationOTP.this,jObject.optString("mobile").toString());

                        progressDialog.dismiss();
                        startActivity(new Intent(VarificationOTP.this,LoginSignUpActivity.class));
                        finish();

                    } else if (jObject.optString("error").equalsIgnoreCase("true") || jObject.optString("error_msg").equalsIgnoreCase("Login credentials are wrong OR Waiting for admin aproval !!")) {
                        Toast.makeText(VarificationOTP.this, jObject.optString("error_msg"), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VarificationOTP.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_CUSTOMER_ID, _userID);
                params.put(KEY_OTP, _otpNumber);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }



}