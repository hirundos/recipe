package com.example.dkdus.cashtrans;

/**
 * Created by ayeon on 2016-12-08.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Recipe_View_Activity extends Activity{
    TextView recipe_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
        recipe_view=(TextView)findViewById(R.id.recipe_view);

        Intent intent=getIntent();
        String rName=intent.getStringExtra("Rcontent");

        recipe_view.setText(rName);

    }
}

