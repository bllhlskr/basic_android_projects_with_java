package com.pandorasoft.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
SeekBar mySeekBar;
TextView textview;
Button button;

public void buttonclicked(View v){
    new CountDownTimer(mySeekBar.getProgress()*1000,1000){
        @Override
        public void onTick(long millisUntilFinished) {
            UpdateTimer((int)millisUntilFinished/1000);
        }

        @Override
        public void onFinish() {

            Log.i("finished","timer all done");
        }
    }.start();

}
public void UpdateTimer(int secondsLeft){

    mySeekBar.setProgress(secondsLeft);
    int minutes =secondsLeft/60;
    int seconds = secondsLeft-(minutes*60);

    String secondString = String.valueOf(seconds);

    if(seconds<10){
        secondString ="0"+ secondString;

    }


    textview.setText(String.valueOf(minutes+" : "+secondString));

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySeekBar = findViewById(R.id.seekBar);
        textview = findViewById(R.id.textView);
        mySeekBar.setMax(600);


       mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           @Override
           public void onProgressChanged(SeekBar seekBar,  int progress, boolean fromUser) {
               UpdateTimer(progress);
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {

           }
       });



        }
    }

