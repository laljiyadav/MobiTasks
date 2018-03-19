package com.example.erlaljiyadav.mobitask.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;

public class QuizResults extends AppCompatActivity {
    TextView correcthead,correct,incorrect,missed,accuracy;
    int correct_s,missed_s,incorrect_s;
    float accuracy_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        correcthead=findViewById(R.id.scoreheadimg);
        correct=findViewById(R.id.correctScore);
        incorrect=findViewById(R.id.incorrectScore);
        missed=findViewById(R.id.missedscore);
        accuracy=findViewById(R.id.acurracy);

        correct_s=getIntent().getIntExtra("correct",0);
        missed_s=getIntent().getIntExtra("missed",0);
        incorrect_s=15-correct_s-missed_s;
        accuracy_s=correct_s/0.15f;

        setData();

    }
    public void setData(){
        correcthead.setText("Your score is: "+correct_s+" points");
        correct.setText(correct_s+"\nCorrect");
        incorrect.setText(incorrect_s+"\nIncorrect");
        missed.setText(missed_s+"\nMissed");
        accuracy.setText(Math.round(accuracy_s)+"%\nAccuracy");
    }
}
