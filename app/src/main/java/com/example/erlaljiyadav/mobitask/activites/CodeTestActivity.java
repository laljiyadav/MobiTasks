package com.example.erlaljiyadav.mobitask.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;

public class CodeTestActivity extends AppCompatActivity {
    TextView  txt_test_color,txt_test_color2;
    EditText login_input_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_test);
        txt_test_color=findViewById(R.id.txt_test_color);

        txt_test_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_test_color.setTextColor(getResources().getColor(R.color.blue_gray));
                txt_test_color2.setTextColor(getResources().getColor(R.color.blue_500));
            }
        });

        txt_test_color2=findViewById(R.id.txt_test_color2);
        txt_test_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_test_color2.setTextColor(getResources().getColor(R.color.blue_gray));
                txt_test_color.setTextColor(getResources().getColor(R.color.blue_500));
                if (login_input_user_name.getText().toString().isEmpty())
                {
                    login_input_user_name.setError("Input");
                }
            }
        });
        login_input_user_name=findViewById(R.id.login_input_user_name);
    }


}
