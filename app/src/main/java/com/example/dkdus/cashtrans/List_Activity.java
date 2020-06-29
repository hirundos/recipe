package com.example.dkdus.cashtrans;

/**
 * Created by ayeon on 2016-12-08.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.room.Room;

import java.util.List;

public class List_Activity extends Activity{
    ListView recipe_listView;
    List<Recipe> recipes;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);

        db = Room.databaseBuilder(this, AppDatabase.class, "recipe-db")
                .allowMainThreadQueries()
                .build();

        recipe_listView=(ListView)findViewById(R.id.recipe_listView);
        recipes = db.recipeDao().getAll();

        final CustomAdapter adapter = new CustomAdapter(this, 0 , recipes);
        recipe_listView.setAdapter(adapter);

        recipe_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),Recipe_View_Activity.class);
                intent.putExtra("Rcontent",recipes.get(position).contents);
                startActivity(intent);
            }
        });
        recipe_listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dlg=new AlertDialog.Builder(List_Activity.this);
                dlg.setTitle("레시피 삭제");
                dlg.setMessage("삭제하시겠습니까?");

                dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        db.recipeDao().delete(recipes.get(position));
                        adapter.notifyDataSetChanged();
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
