package com.example.dkdus.cashtrans;

/**
 * Created by ayeon on 2016-12-08.
 */

import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Input_Activity extends Activity{

    RadioGroup Kind;
    Button input, input_back;
    EditText recipe,name;
    myDBHelper Recipe;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_input);

        Kind=(RadioGroup)findViewById(R.id.Kind);
        input=(Button)findViewById(R.id.btn_input);
        input_back=(Button)findViewById(R.id.input_back);
        recipe=(EditText)findViewById(R.id.recipe);
        name=(EditText)findViewById(R.id.name);
        Recipe=new myDBHelper(this);

        input.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                sqlDB = Recipe.getWritableDatabase();
                int id = Kind.getCheckedRadioButtonId();
                RadioButton setKind = (RadioButton) findViewById(id);
                if (name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름이 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else if (recipe.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "레시피가 입력되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else if (Kind.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "음식 종류가 선택되지 않았습니다", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        sqlDB.execSQL("INSERT INTO Recipe VALUES ('" + name.getText().toString() + "','" + setKind.getText().toString() + "','" + recipe.getText().toString() + "');");
                        Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        recipe.setText("");
                        Kind.clearCheck();
                    } catch (SQLException e) {
                        Toast.makeText(getApplicationContext(), "저장 실패", Toast.LENGTH_SHORT).show();
                    }
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
