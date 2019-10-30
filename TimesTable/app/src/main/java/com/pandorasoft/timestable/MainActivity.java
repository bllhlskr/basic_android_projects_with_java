package com.pandorasoft.timestable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final ArrayList<Integer> numbers= new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);
        numbers.add(7);
        numbers.add(8);
        numbers.add(9);
        numbers.add(10);




        final ListView myListView= findViewById(R.id.myListView);
        final SeekBar mySeekBar = findViewById(R.id.seekBar);

        final ArrayAdapter<Integer> mtAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,numbers);
        myListView.setAdapter(mtAdapter);


        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                Toast.makeText(getApplicationContext(),"seekNumber"+String.valueOf(progress),Toast.LENGTH_SHORT).show();

                for(int i = 0;i<numbers.size();i++){
                    int counter = 1;

                   counter= (i+1)*progress;
                   numbers.set(i,counter);
                   counter =1;

                    myListView.setAdapter(mtAdapter);
                }



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
