package com.example.dkdus.cashtrans.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.dkdus.cashtrans.AppDatabase;
import com.example.dkdus.cashtrans.R;
import com.example.dkdus.cashtrans.model.Recipe;


public class Input_Activity extends AppCompatActivity {

    RadioGroup foodKind;
    Button input, input_back;
    EditText contents, name;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_input);

        db= Room.databaseBuilder(this, AppDatabase.class,"recipe-db" )
                .allowMainThreadQueries()
                .build();

        foodKind=(RadioGroup)findViewById(R.id.kindRadioBtn);
        input=(Button)findViewById(R.id.btn_input);
        input_back=(Button)findViewById(R.id.input_back);
        contents=(EditText)findViewById(R.id.contents);
        name=(EditText)findViewById(R.id.name);

        input.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                int id = foodKind.getCheckedRadioButtonId();
                RadioButton kindDetail = (RadioButton) findViewById(id);
                if (name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름이 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else if (contents.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "레시피가 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else if (foodKind.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "음식 종류가 선택되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else {
                    db.recipeDao().insert(new Recipe(
                            name.getText().toString(), kindDetail.getText().toString(), contents.getText().toString(),""));
                    name.setText("");
                    contents.setText("");
                    foodKind.clearCheck();
                }
            }
        });

        input_back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }

}
