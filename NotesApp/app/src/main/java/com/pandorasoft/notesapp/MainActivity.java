package com.pandorasoft.notesapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
ListView listView;
    public static ArrayList<String> Notes;
    public static ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        Notes= new ArrayList<>();
        sharedPreferences = getApplicationContext().getSharedPreferences("com.pandorasoft.notesapp",
                Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if(set == null){
            Notes.add("Halis bilal kara");
        }else{
            Notes = new ArrayList<String>(set);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,Notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("note",Notes.get(position));
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              final int pos = position;
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Delete item? ")
                        .setMessage("Are You Sure ?")
                        .setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Notes.remove(pos);
                                        arrayAdapter.notifyDataSetChanged();

                                        HashSet<String> set = new HashSet<>(MainActivity.Notes);
                                        sharedPreferences.edit().putStringSet("notes",set).apply();
                                    }
                                }).setNegativeButton("No",null).show();

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.addNote:
                Notes.add("New Note");
                HashSet<String> set = new HashSet<>(MainActivity.Notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
                arrayAdapter.notifyDataSetChanged();
                default:
                    return super.onOptionsItemSelected(item);
        } }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }
}
