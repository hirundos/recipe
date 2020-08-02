package com.example.dkdus.cashtrans.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.dkdus.cashtrans.database.AppDatabase;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListViewModel extends AndroidViewModel {
    AppDatabase db;

    public ListViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application, AppDatabase.class, "recipe-db")
                .build();
    }

    public List<Recipe> getAll(){
        List<Recipe> recipe = null;
        try {
            recipe = new GetAsyncTask(db.recipeDao()).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private static class GetAsyncTask extends AsyncTask<Void, Void, List<Recipe>> {
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
