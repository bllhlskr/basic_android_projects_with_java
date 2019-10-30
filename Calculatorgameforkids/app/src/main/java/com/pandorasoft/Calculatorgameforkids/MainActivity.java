package com.pandorasoft.Calculatorgameforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
TextView time,question,questionpercent,result;
Button goButton,button5,button6,button7,button8,playAgainButton;
ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0,numberofQuestions =0;
    ConstraintLayout layout2;


int timer=26;
public void go(View v){

    layout2.setVisibility(View.VISIBLE);
    goButton.setVisibility(View.INVISIBLE);
}





   public void PlayAgain(View v){

        score =0;
        numberofQuestions=0;
        time.setText("30s");
        newQuestion();
       questionpercent.setText(String.valueOf(score)+"/" +String.valueOf(numberofQuestions));
       playAgainButton.setVisibility(View.INVISIBLE);
       result.setText(" ");
       button5.setEnabled(true);
       button6.setEnabled(true);
       button7.setEnabled(true);
       button8.setEnabled(true);


       new CountDownTimer(1000*30,1000){

           @Override
           public void onTick(long millisUntilFinished) {
               time.setText(String.valueOf(millisUntilFinished/1000)+"s");
               playAgainButton.setVisibility(View.INVISIBLE);
           }

           @Override
           public void onFinish() {

               result.setText("DONE!");
               playAgainButton.setVisibility(View.VISIBLE);
               button5.setEnabled(false);
               button6.setEnabled(false);
               button7.setEnabled(false);
               button8.setEnabled(false);


           }
       }.start();

   }
   public void answer(View v){

        if(Integer.toString( locationOfCorrectAnswer).equals( v.getTag().toString())){
            result.setText("Correct");
            result.setVisibility(View.VISIBLE);
            numberofQuestions++;

            score++;
        }else{
            result.setText("Wrong");
            result.setVisibility(View.VISIBLE);
            numberofQuestions++;
        }
        newQuestion();

        questionpercent.setText(String.valueOf(score)+"/" +String.valueOf(numberofQuestions));

    }
    public void newQuestion(){
        Random r = new Random();
        int a = r.nextInt(21);
        int b = r.nextInt(21);
        question.setText(String.valueOf(a)+"+" +String.valueOf(b));
        locationOfCorrectAnswer = r.nextInt(4);
        answer.clear();

        for(int i=0; i<4;i++){

            if(locationOfCorrectAnswer==i){
                answer.add(a+b);

            }else{
                int wrongasnwer = r.nextInt(41);

                while(wrongasnwer == a+b){
                    wrongasnwer = r.nextInt(41);

                }
                answer.add(wrongasnwer);
            }
        }
        button5.setText(String.valueOf(answer.get(0)));
        button6.setText(String.valueOf(answer.get(1)));
        button7.setText(String.valueOf(answer.get(2)));
        button8.setText(String.valueOf(answer.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.go_button);
        time = findViewById(R.id.time);
        question = findViewById(R.id.question);
        questionpercent = findViewById(R.id.question_percent);
        button6 = findViewById(R.id.button6);
        button5 = findViewById(R.id.button5);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        result = findViewById(R.id.textView5);
        playAgainButton = findViewById(R.id.Play_again);
        layout2 = findViewById(R.id.constrait_2);

         newQuestion();

         PlayAgain(findViewById(R.id.time));





    }
}
