package com.example.dkdus.cashtrans.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import com.example.dkdus.cashtrans.AppDatabase;
import com.example.dkdus.cashtrans.CustomAdapter;
import com.example.dkdus.cashtrans.R;
import com.example.dkdus.cashtrans.databinding.RecipeListBinding;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class List_Activity extends AppCompatActivity {
    private static int REQUEST_CODE = 1001;
    List<Recipe> recipes;
    AppDatabase db;
    RecipeListBinding binding;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        binding = DataBindingUtil.setContentView(this, R.layout.recipe_list);

        db = Room.databaseBuilder(this, AppDatabase.class, "recipe-db")
                .build();

        try {
            recipes = new GetAsyncTask(db.recipeDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new CustomAdapter(this, 0 , recipes);
        binding.recipeListView.setAdapter(adapter);

        binding.recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), RecipeDetailActivity.class);
                intent.putExtra("sendRecipe",adapter.getItem(position));
                startActivityForResult(intent,REQUEST_CODE);
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeDetailActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            try {
                recipes = new GetAsyncTask(db.recipeDao()).execute().get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            adapter.dataChange(recipes);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private static class GetAsyncTask extends AsyncTask<Void, Void, List<Recipe>>{
        RecipeDao mRecipeDao;

        public GetAsyncTask(RecipeDao RecipeDao) {
            this.mRecipeDao = RecipeDao;
        }

        @Override
        protected List<Recipe> doInBackground(Void... voids) {
            return mRecipeDao.getAll();
        }
    }
}
