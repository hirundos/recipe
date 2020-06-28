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
    SQLiteDatabase sqlDB;
    myDBHelper Recipe;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
        recipe_view=(TextView)findViewById(R.id.recipe_view);
        Recipe=new myDBHelper(this);
        sqlDB=Recipe.getReadableDatabase();

        Intent intent=getIntent();
        String rName=intent.getStringExtra("rName");
        setTitle(rName);
        cursor=sqlDB.rawQuery("SELECT * FROM recipe WHERE rName='"+rName+"';",null);

        try{
            String text = null;
            while(cursor.moveToNext()) {
                text = cursor.getString(2);
            }
            recipe_view.setText(text);
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),"불러오기 실패",Toast.LENGTH_SHORT).show();
        }
    }
}

