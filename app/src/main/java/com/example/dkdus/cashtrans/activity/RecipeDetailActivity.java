package com.example.dkdus.cashtrans.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;

import com.example.dkdus.cashtrans.AppDatabase;
import com.example.dkdus.cashtrans.R;
import com.example.dkdus.cashtrans.databinding.RecipeViewBinding;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RecipeDetailActivity extends AppCompatActivity {
    RecipeViewBinding binding;
    AppDatabase db;
    Recipe recipe;
    String date;
    boolean isUpdate = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.recipe_view);

        db= Room.databaseBuilder(this, AppDatabase.class,"recipe-db" )
                .build();

        date = LocalDate.now().format(formatter);

        Intent intent=getIntent();
        if(intent != null) {
            recipe = intent.getParcelableExtra("sendRecipe");
            if(recipe != null){
                isUpdate = true;
                binding.name.setText(recipe.getName());
                binding.content.setText(recipe.getContents());
                binding.kind.setSelection(WhatKind(recipe.getKind()));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_update,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_store:
                if(isUpdate){
                    RecipeUpdate();
                }else{
                    RecipeAdd();
                }
                return true;
            case R.id.menu_delete:
                RecipeDelete();
               return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void RecipeAdd(){
        Recipe addRecipe = new Recipe(
                binding.name.getText().toString(), binding.kind.getSelectedItem().toString(), binding.content.getText().toString(),date);
        new InsertAsyncTask(db.recipeDao()).execute(addRecipe);
        setResult(RESULT_OK);
        finish();
    }
    void RecipeUpdate(){
        recipe.setName(binding.name.getText().toString());
        recipe.setContents(binding.content.getText().toString());
        recipe.setKind(binding.kind.getSelectedItem().toString());

        new UpdateAsyncTask(db.recipeDao()).execute(recipe);
        setResult(RESULT_OK);
        finish();
    }
    void RecipeDelete(){

        new DeleteAsyncTask(db.recipeDao()).execute(recipe);
        setResult(RESULT_OK);
        finish();
    }
    int WhatKind(String kind){
        switch (kind){
            case "한식":
                return 0;
            case "중식":
                return 1;
            case "양식":
                return 2;
            case "일식":
                return 3;
            default:
                return -1;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Recipe, Void, Void>{
        RecipeDao mRecipeDao;

        public InsertAsyncTask(RecipeDao mRecipeDao) {
            this.mRecipeDao = mRecipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mRecipeDao.insert(recipes[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Recipe, Void, Void>{
        RecipeDao mRecipeDao;

        public UpdateAsyncTask(RecipeDao mRecipeDao) {
            this.mRecipeDao = mRecipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mRecipeDao.update(recipes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Recipe, Void, Void>{
        RecipeDao mRecipeDao;

        public DeleteAsyncTask(RecipeDao mRecipeDao) {
            this.mRecipeDao = mRecipeDao;
        }

        @Override
        protected Void doInBackground(Recipe... recipes) {
            mRecipeDao.delete(recipes[0]);
            return null;
        }
    }
}

