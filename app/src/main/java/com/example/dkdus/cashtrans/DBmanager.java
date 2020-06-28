package com.example.dkdus.cashtrans;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ayeon on 2016-12-08.
 */

public class DBmanager extends SQLiteOpenHelper{
        public  DBmanager(Context context)
        {
            super(context,"groupDB",null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE member (gId CHAR(30) PRIMARY KEY,gPwd CHAR(30), gName CHAR(30),gPhone CHAR(30), gEmail CHAR(50));");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS member");
            onCreate(sqLiteDatabase);
        }
    }


