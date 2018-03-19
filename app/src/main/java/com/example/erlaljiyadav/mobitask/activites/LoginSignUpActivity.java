package com.example.erlaljiyadav.mobitask.activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.example.erlaljiyadav.mobitask.utility.commonFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.erlaljiyadav.mobitask.configs.Configs.*;


public class LoginSignUpActivity extends AppCompatActivity {

    EditText signup_input_user_name,signup_input_email,signup_input_mobile,signup_input_password,signup_input_confirm_password,login_mobile,login_password;
    TextView txt_login,txt_signup;
    TextInputLayout input_user_name;
    LinearLayout login_layout,signup_layout;
    final int sdk = android.os.Build.VERSION.SDK_INT;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        //.........Call init().........
        init();
        //.............Set Click Events

        if (SaveSharedPreference.getPrefRemember(LoginSignUpActivity.this))
        {
            startActivity(new Intent(LoginSignUpActivity.this,HomeBottomMenu.class));
            finish();
        }

        if (!SaveSharedPreference.getUserID(LoginSignUpActivity.this).isEmpty())
        {
            startActivity(new Intent(LoginSignUpActivity.this,VarificationOTP.class));
            finish();
        }

    }

    private void init()
    {
        //............... Input EditText views.............
        signup_input_user_name=findViewById(R.id.signup_input_user_name);
        signup_input_email=findViewById(R.id.signup_input_email);
        signup_input_mobile=findViewById(R.id.signup_input_mobile);
        signup_input_password=findViewById(R.id.signup_input_password);
        signup_input_confirm_password=findViewById(R.id.signup_input_confirm_password);
        login_mobile=findViewById(R.id.login_mobile);
        login_password=findViewById(R.id.login_password);


        //................Layout for Signup and Login

        login_layout=findViewById(R.id.login_layout);
        signup_layout=findViewById(R.id.signup_layout);
        //......................TextView View................
        txt_login=findViewById(R.id.txt_login);
        txt_signup=findViewById(R.id.txt_signup);
        signup_layout.setVisibility(View.GONE);
//...........Set Login Active By Default
        txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
        txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder));
        txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
        txt_signup.setTextColor(getResources().getColor(R.color.blue_500));

        //................... Set Progress Dialoge

        progressDialog=new ProgressDialog(this);

//        //.............Set Click Events
        txt_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                signup_layout.setVisibility(View.GONE);
                login_layout.setVisibility(View.VISIBLE);

                txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
                txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder));
                txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
                txt_signup.setTextColor(getResources().getColor(R.color.blue_500));

                if (!login_mobile.getText().toString().isEmpty())
                {
                    validationForLogin();
                }else
                {

                }

            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_layout.setVisibility(View.GONE);
                signup_layout.setVisibility(View.VISIBLE);

                txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
                txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder) );
                txt_login.setTextColor(getResources().getColor(R.color.blue_500));
                txt_signup.setTextColor(getResources().getColor(R.color.blue_gray));

                if (!signup_input_user_name.getText().toString().isEmpty())
                {
                    validation();
                }else
                {
                    signup_input_user_name.setError("Enter Full Name");
                    signup_input_user_name.requestFocus();
                }
            }
        });



//        txt_login.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void onClick(View v) {
//                signup_layout.setVisibility(View.GONE);
//                login_layout.setVisibility(View.VISIBLE);
//
//                if(sdk > android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
//                    txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder));
//                    txt_signup.setTextColor(getResources().getColor(R.color.blue_500));
//
//                } else {
//                    txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
//                    txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder));
//                    txt_signup.setTextColor(getResources().getColor(R.color.blue_500));
//
//                }
//            }
//        });
//        txt_signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login_layout.setVisibility(View.GONE);
//                signup_layout.setVisibility(View.VISIBLE);
//
//                if(sdk > android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                    txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder) );
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_500));
//                    txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid));
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
//                } else {
//                    txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder) );
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_500));
//                    txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid));
//                    txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
//                }
//
//            }
//        });

    }

    // For Validation SignUP

    private void validation() {
        if (signup_input_user_name.getText().toString().isEmpty()  || signup_input_user_name.getText().length() == 0) {
            signup_input_user_name.setError("Enter Full Name");
            signup_input_user_name.requestFocus();
        } else if (signup_input_email.getText().toString().isEmpty() || signup_input_email.getText().toString().length() == 0||!commonFunction.isValideEmailPattern(signup_input_email.getText().toString())) {
            signup_input_email.setError("Enter Email Name");
            signup_input_email.requestFocus();

        } else if (signup_input_mobile.getText().toString().isEmpty() || signup_input_mobile.getText().toString().length() == 0 ) {
            signup_input_mobile.setError("Enter valid mobile number");
            signup_input_mobile.requestFocus();
        } else if (signup_input_password.getText().toString().isEmpty() || signup_input_password.getText().toString().length() == 0) {

            signup_input_password.setError("Enter Valid password");
            signup_input_password.requestFocus();

        }else {
            //call for this parsing function

            signUpParse();

        }

    }

    //.....For Login Validation


    private void validationForLogin() {
        if (login_mobile.getText().toString().isEmpty() || login_mobile.getText().toString().length() == 0 ) {
            login_mobile.setError("Enter Register mobile number");
            login_mobile.requestFocus();
        } else if (login_password.getText().toString().isEmpty() || login_password.getText().toString().length() == 0) {

            login_password.setError("Enter Valid password");
            login_password.requestFocus();

        }else {
            //call for this parsing function

            loginParse();

        }

    }


    //.....Define resitraion function..........

    private void signUpParse() {
        progressDialog.show();
        progressDialog.setMessage("Registering...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configs.ADDCOUSTOMER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);
                    Log.e("Response",jsonObject.toString());
                    if (jsonObject.optString("error").toString().equalsIgnoreCase("false"))
                    {

                        Toast.makeText(LoginSignUpActivity.this, jsonObject.optString("Message"),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        signup_layout.setVisibility(View.GONE);
                        login_layout.setVisibility(View.VISIBLE);
                        txt_login.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_solid) );
                        txt_signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_boarder));
                        txt_login.setTextColor(getResources().getColor(R.color.blue_gray));
                        txt_signup.setTextColor(getResources().getColor(R.color.blue_500));

                        //..............Save Cusstomer ID......................
                        SaveSharedPreference.setUserID(LoginSignUpActivity.this,jsonObject.optString("customer_id").toString());
                        //...............Navigate Verfication.........

                        startActivity(new Intent(LoginSignUpActivity.this,VarificationOTP.class));
                        finish();

                    }else {
                        Toast.makeText(LoginSignUpActivity.this, jsonObject.optString("Message"),Toast.LENGTH_LONG).show();
                        Log.e("error_msg",jsonObject.optString("error_msg"));
                        progressDialog.dismiss();
                    }
                }catch (JSONException e)
                {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginSignUpActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USER_NAME, signup_input_user_name.getText().toString());
                params.put(KEY_EMAIL, signup_input_email.getText().toString());
                params.put(KEY_MOBILE, signup_input_mobile.getText().toString());
                params.put(KEY_PASSWORD, signup_input_password.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    //.......Define function for login..........

    private void loginParse()
    {
        progressDialog.show();
        progressDialog.setMessage("Login...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configs.LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject= new JSONObject(response);
                    Log.e("Response",jsonObject.toString());
                    if (jsonObject.optString("error").toString().equalsIgnoreCase("false"))
                    {
                        progressDialog.dismiss();
                        Log.e("error_msg",jsonObject.optString("Message"));

                        //........Save Login Response in SharedPreference.............
                        SaveSharedPreference.setUserID(LoginSignUpActivity.this,jsonObject.optString("customer_id"));
                        SaveSharedPreference.setUserName(LoginSignUpActivity.this,jsonObject.optString("name"));
                        SaveSharedPreference.setUserEMAIL(LoginSignUpActivity.this,jsonObject.optString("mobile"));
                        SaveSharedPreference.setMobile(LoginSignUpActivity.this,jsonObject.optString("email"));
                        SaveSharedPreference.setPrefRemember(LoginSignUpActivity.this,true);
                        Log.e("shared_save_data","Success");
                        startActivity(new Intent(LoginSignUpActivity.this,HomeBottomMenu.class));
                        finish();

                    }else {
                        Toast.makeText(LoginSignUpActivity.this, jsonObject.optString("Message"),Toast.LENGTH_LONG).show();
                        Log.e("error_msg",jsonObject.optString("Message"));
                        progressDialog.dismiss();
                    }
                }catch (JSONException e)
                {

                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginSignUpActivity.this, error.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_MOBILE, login_mobile.getText().toString());
                params.put(KEY_PASSWORD, login_password.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

}
