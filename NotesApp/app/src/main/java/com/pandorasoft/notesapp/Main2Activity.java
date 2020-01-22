package com.pandorasoft.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class Main2Activity extends AppCompatActivity {
TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textview = findViewById(R.id.editText);

        Intent intent = getIntent();
        String whatYouSent = intent.getStringExtra("note");


        textview.setText(intent.getStringExtra("note"));






        textview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                Intent intent = getIntent();
                int come = intent.getIntExtra("position",0);
                MainActivity.Notes.set(come,textview.getText().toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.pandorasoft.notesapp",
                        Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.Notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }
        });


    }
}
