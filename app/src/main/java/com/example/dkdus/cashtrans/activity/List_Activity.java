package com.example.dkdus.cashtrans.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.dkdus.cashtrans.util.CustomAdapter;
import com.example.dkdus.cashtrans.R;
import com.example.dkdus.cashtrans.databinding.RecipeListBinding;
import com.example.dkdus.cashtrans.model.Recipe;
import com.example.dkdus.cashtrans.viewmodel.ListViewModel;
import java.util.List;

public class List_Activity extends AppCompatActivity {
    private static int REQUEST_CODE = 1001;
    String searchQuery;
    List<Recipe> recipes;
    ListViewModel viewModel;
    RecipeListBinding binding;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_list);
        binding = DataBindingUtil.setContentView(this, R.layout.recipe_list);
        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        recipes = viewModel.getAll();
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

        binding.searchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), Search_Activity.class);
                String send = "https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query="+searchQuery;
                searchIntent.putExtra("URL",send);
                startActivity(searchIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            recipes = viewModel.getAll();
            adapter.dataChange(recipes);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                if(adapter.getCount() == 0){
                    searchQuery = query;
                    binding.searchResult.setVisibility(View.VISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                binding.searchResult.setVisibility(View.GONE);
                if(newText.length() > 0){
                    adapter.getFilter().filter(newText);
                } else{
                    adapter.dataChange(recipes);
                }
                return false;
            }
        });
        return true;
    }

}
