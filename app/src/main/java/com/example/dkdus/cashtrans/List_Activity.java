package com.example.dkdus.cashtrans;

/**
 * Created by ayeon on 2016-12-08.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class List_Activity extends Activity{
    SQLiteDatabase sqlDB;
    myDBHelper Recipe;
    ListView recipe_listView;
    Cursor adapter_cursor, delete_cursor;
    ArrayList<String> listArray;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        recipe_listView=(ListView)findViewById(R.id.recipe_listView);
        Recipe=new myDBHelper(this);
        sqlDB=Recipe.getWritableDatabase();
        //list=new ArrayList<HashMap<String, String>>();
        listArray=new ArrayList<String>();
        //HashMap<String, String> hash_list = null;
        adapter_cursor=sqlDB.rawQuery("SELECT rName, rKind FROM recipe;",null);

        try {
            while (adapter_cursor.moveToNext()) {
                String name=adapter_cursor.getString(adapter_cursor.getColumnIndex("rName"));
                listArray.add(name);
            }
            arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listArray);
            recipe_listView.setAdapter(arrayAdapter);

        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_SHORT).show();
        }
        recipe_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key=listArray.get(position);
                Intent intent=new Intent(getApplicationContext(),Recipe_View_Activity.class);
                intent.putExtra("rName",key);
                startActivity(intent);
            }
        });
        recipe_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg=new AlertDialog.Builder(List_Activity.this);
                dlg.setTitle("레시피 삭제");
                dlg.setMessage("삭제하시겠습니까?");

                final String key=listArray.get(position);
                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        try {
                            sqlDB.execSQL("DELETE FROM Recipe WHERE rName = '"+key+"';");
                            listArray.remove(position);
                            arrayAdapter.notifyDataSetChanged();
                        }catch (SQLException e){
                            Toast.makeText(getApplicationContext(),"삭제 실패",Toast.LENGTH_SHORT).show();
                            delete_cursor.moveToFirst();
                        }
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dlg.show();
                return false;
            }
        });

    }

}
