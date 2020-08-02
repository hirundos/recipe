package com.example.dkdus.cashtrans.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.dkdus.cashtrans.database.AppDatabase;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;

public class DetailViewModel extends AndroidViewModel {
    AppDatabase db;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application, AppDatabase.class, "recipe-db")
                .build();

    }

    public void insert(Recipe recipe){
        new InsertAsyncTask(db.recipeDao()).execute(recipe);
    }
    public void update(Recipe recipe){
        new UpdateAsyncTask(db.recipeDao()).execute(recipe);
    }

    public void delete(Recipe recipe){
        new DeleteAsyncTask(db.recipeDao()).execute(recipe);

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

    private static class UpdateAsyncTask extends AsyncTask<Recipe, Void, Void> {
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
