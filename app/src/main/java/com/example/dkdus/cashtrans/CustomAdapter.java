package com.example.dkdus.cashtrans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dkdus.cashtrans.model.Recipe;

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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.recipe_listview, null, true);

            TextView name = convertView.findViewById(R.id.rName);
            TextView time = convertView.findViewById(R.id.rTime);

            holder.name = name;
            holder.time = time;

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(recipe.get(position).getName());
        holder.time.setText(recipe.get(position).getTime());

        return convertView;
    }

    public void dataChange(List<Recipe> recipes){
        this.recipe = recipes;
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public Recipe getItem(int position) {
        return recipe.get(position);
    }
}
