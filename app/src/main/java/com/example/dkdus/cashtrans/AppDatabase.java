package com.example.dkdus.cashtrans;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;

@Database(entities = {Recipe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
}
