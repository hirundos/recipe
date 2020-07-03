package com.example.dkdus.cashtrans.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dkdus.cashtrans.R;


public class Search_Activity extends Activity{
    Button naver,google;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);
        naver=(Button)findViewById(R.id.search_naver);
        google=(Button)findViewById(R.id.search_google);

        naver.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://naver.com"));
                startActivity(intent);
            }
        });
        google.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
                startActivity(intent);
            }
        });
    }
}
