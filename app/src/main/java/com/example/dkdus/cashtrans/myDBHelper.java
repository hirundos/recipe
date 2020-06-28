package com.example.dkdus.cashtrans;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayeon on 2016-12-08.
 */


public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context){
        super(context, "Recipe",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Recipe(rName CHAR(15), rKind CHAR(15), rTEXT CHAR(500));");
        db.execSQL("CREATE TABLE member (gId CHAR(30) PRIMARY KEY,gPwd CHAR(30), gName CHAR(30),gPhone CHAR(30), gEmail CHAR(50));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Recipe");
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }
    public void delete(SQLiteDatabase db,String d_text){
        //db.execSQL("DELETE * FROM Recipe");
    }
}
