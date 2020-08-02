package com.example.dkdus.cashtrans.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.dkdus.cashtrans.database.AppDatabase;
import com.example.dkdus.cashtrans.R;
import com.example.dkdus.cashtrans.databinding.RecipeViewBinding;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.model.RecipeDao;
import com.example.dkdus.cashtrans.viewmodel.DetailViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class RecipeDetailActivity extends AppCompatActivity {
    RecipeViewBinding binding;
    Recipe recipe;
    String date;
    boolean isUpdate = false;
    DetailViewModel viewModel;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.recipe_view);

        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
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
                if(isUpdate)
                    RecipeUpdate();
                else
                    RecipeAdd();
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
        viewModel.insert(addRecipe);
        setResult(RESULT_OK);
        finish();
    }
    void RecipeUpdate(){
        recipe.setName(binding.name.getText().toString());
        recipe.setContents(binding.content.getText().toString());
        recipe.setKind(binding.kind.getSelectedItem().toString());
        viewModel.update(recipe);
        setResult(RESULT_OK);
        finish();
    }
    void RecipeDelete(){
        viewModel.delete(recipe);
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


}

