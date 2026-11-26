package com.example.pokemon_datablind.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon_datablind.R;

import java.util.List;

public class RecylerTypesAdapter extends RecyclerView.Adapter<RecylerTypesAdapter.TypeViewHolder> {

    private List<String> types;

    public RecylerTypesAdapter(List<String> types) {
        this.types = types;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_pokemon, parent, false);
        return new TypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        String type = types.get(position);
        holder.typeTextView.setText(type);
        holder.cardView.setCardBackgroundColor(Color.parseColor(getColor(type)));
    }
    public String getColor(String type){
        String color = null;
        switch (type){
            case "Normal": color =  "#A8A77A"; break;
            case "Fire": color =  "#EE8130"; break;
            case "Water": color =  "#6390F0"; break;
            case "Electric": color =  "#F7D02C"; break;
            case "Grass": color =  "#7AC74C"; break;
            case "Ice": color =  "#96D9D6"; break;
            case "Fighting": color =  "#C22E28"; break;
            case "Poison": color =  "#A33EA1"; break;
            case "Ground": color =  "#E2BF65"; break;
            case "Flying": color =  "#A98FF3"; break;
            case "Psychic": color =  "#F95587"; break;
            case "Bug": color =  "#A6B91A"; break;
            case "Rock": color =  "#B6A136"; break;
            case "Ghost": color =  "#735797"; break;
            case "Dragon": color =  "#6F35FC"; break;
            case "Dark": color =  "#705746"; break;
            case "Steel": color =  "#B7B7CE"; break;
            case "Fairy": color =  "#D685AD"; break;
        }
        return color;
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public static class TypeViewHolder extends RecyclerView.ViewHolder {
        TextView typeTextView;
        CardView cardView;
        public TypeViewHolder(View itemView) {
            super(itemView);
            typeTextView = itemView.findViewById(R.id.types);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
