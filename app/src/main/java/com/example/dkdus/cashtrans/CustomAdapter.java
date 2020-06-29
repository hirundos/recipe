package com.example.dkdus.cashtrans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Recipe> {

    List<Recipe> recipe;
    Context context;

    public CustomAdapter(@NonNull Context context, int resource, List<Recipe> recipes) {
        super(context, resource);
        this.context = context;
        this.recipe = recipes;
    }

    @Override
    public int getCount() {
        return recipe.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.recipe_list_listview, null, true);

        TextView kind = convertView.findViewById(R.id.rKind);
        TextView name = convertView.findViewById(R.id.rName);

        kind.setText(recipe.get(position).kind);
        name.setText(recipe.get(position).name);

        return convertView;
    }
}
