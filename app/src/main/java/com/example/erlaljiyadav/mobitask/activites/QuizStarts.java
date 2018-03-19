package com.example.erlaljiyadav.mobitask.activites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.modals.QuizStartModel;

import java.util.ArrayList;
import java.util.Collections;

public class QuizStarts extends AppCompatActivity {
    ImageView questionImage;
    TextView questionNumber,question,timeRemaining,next,status;
    Button answer1,answer2,answer3,answer4;
    int questioncount=1;
    CountDownTimer countDownTimer;
    Dialog dialog;
    Toolbar toolbar;
    int correctcount=0;
    int missedcount=0;
    boolean doubleBackToExitPressedOnce = false;
    AlertDialog alertDialog;
    TextView toolUname;
    ArrayList<QuizStartModel> quizlist=new ArrayList<>();

    ArrayList<String> questions=new ArrayList<>();
    ArrayList<String> ans1=new ArrayList<>();
    ArrayList<String> ans2=new ArrayList<>();
    ArrayList<String> ans3=new ArrayList<>();
    ArrayList<String> ans4=new ArrayList<>();
    ArrayList<String> correctans=new ArrayList<>();

    QuizStartModel quizStartModel;
    int correctAnswer=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_starts);

        timeRemaining=findViewById(R.id.timeremaining);
//        questionImage=findViewById(R.id.question_image);
        question=findViewById(R.id.question);
        questionNumber=findViewById(R.id.question_number);
        answer1=findViewById(R.id.answer1);
        answer2=findViewById(R.id.answer2);
        answer3=findViewById(R.id.answer3);
        answer4=findViewById(R.id.answer4);
    }
    private void answerAlertBox(int flag)
    {
        questioncount++;

        if (questioncount<=15){
            next.setText("Next Question");

            if (flag==1){
                correctcount++;
                status.setText("Correct Answer");
                status.setTextColor(getResources().getColor(R.color.light_green));
                next.setBackgroundColor(getResources().getColor(R.color.light_green));
            }
            else if (flag==0){
                status.setText("Oops! Inorrect Answer");
                status.setTextColor(getResources().getColor(R.color.red500));
                next.setBackgroundColor(getResources().getColor(R.color.red500));
            }
            else {
                missedcount++;
                status.setText("Oops! Time Out");
                status.setTextColor(getResources().getColor(R.color.red500));
                next.setBackgroundColor(getResources().getColor(R.color.red500));
            }
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setQuestion();
                    questionNumber.setText(String .valueOf(questioncount)+"/15");
                    dialog.dismiss();
                    timer();
                }
            });

        }


        else {
            if (flag==1){
                correctcount++;
                status.setText("Correct answer\nQuiz Completed");
                status.setTextColor(getResources().getColor(R.color.light_green));
                next.setBackgroundColor(getResources().getColor(R.color.light_green));
                next.setText("See Result");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent=new Intent(QuizStarts.this,QuizResults.class);
                        intent.putExtra("correct", correctcount);
                        intent.putExtra("missed", missedcount);


                        startActivity(intent);
                        finish();
                    }
                });
            }
            else {
                status.setText("InCorrect answer\nQuiz Completed");
                status.setTextColor(getResources().getColor(R.color.red500));
                next.setBackgroundColor(getResources().getColor(R.color.light_green));
                next.setText("See Result");
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Intent intent=new Intent(QuizStarts.this,QuizResults.class);
                        intent.putExtra("correct", correctcount);
                        intent.putExtra("missed", missedcount);


                        startActivity(intent);
                        finish();
                    }
                });
            }


        }




        dialog.show();
    }



    @Override
    protected void onResume() {
        countDownTimer.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ////////////////cancel timer on activity pause//////////////////
        countDownTimer.cancel();
    }

    ///////////////////timer for quiz////////////////////////////
    public void timer(){
        countDownTimer=new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {

                if (l/1000>=10){
                    toolUname.setText("00:"+String.valueOf(l/1000));
                }
                else {
                    toolUname.setText("00:0"+String.valueOf(l/1000));

                }
            }

            @Override
            public void onFinish() {
                answerAlertBox(2);

            }
        }.start();
    }
    //    set questions and answers//////////////////////
    private void getQuestions(){
        quizlist= (ArrayList<QuizStartModel>) getIntent().getSerializableExtra("quizlist");

        for (int i=0;i<quizlist.size();i++){
            quizStartModel=quizlist.get(i);
            ArrayList<String> ans=new ArrayList<>();

            ans.add(quizStartModel.getAnswer1());
            ans.add(quizStartModel.getAnswer2());
            ans.add(quizStartModel.getAnswer3());
            ans.add(quizStartModel.getAnswer4());

            Collections.shuffle(ans);

            questions.add(quizStartModel.getQuestion());
            ans1.add(ans.get(0));
            ans2.add(ans.get(1));
            ans3.add(ans.get(2));
            ans4.add(ans.get(3));
            correctans.add(quizStartModel.getCorrect_answer());
        }
        setQuestion();
    }


    private void setQuestion(){
        question.setText(Html.fromHtml(questions.get(questioncount)));
        answer1.setText(Html.fromHtml(ans1.get(questioncount)));
        answer2.setText(Html.fromHtml(ans2.get(questioncount)));
        answer3.setText(Html.fromHtml(ans3.get(questioncount)));
        answer4.setText(Html.fromHtml(ans4.get(questioncount)));

    }

}
