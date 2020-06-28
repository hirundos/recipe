package com.example.dkdus.cashtrans;

/**
 * Created by ayeon on 2016-12-08.
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RmainActivity  extends AppCompatActivity {
    Button input,list,search;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_main);

        input=(Button)findViewById(R.id.input);
        list=(Button)findViewById(R.id.list);
        search=(Button)findViewById(R.id.search);

        input.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),Input_Activity.class);
                startActivity(intent);
            }
        });

        list.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),List_Activity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(),Search_Activity.class);
                startActivity(intent);
            }
        });

    }
}
