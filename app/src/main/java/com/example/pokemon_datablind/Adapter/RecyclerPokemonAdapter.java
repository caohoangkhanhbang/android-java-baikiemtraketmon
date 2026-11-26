package com.example.pokemon_datablind.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon_datablind.R;
import com.example.pokemon_datablind.SqliteClass.Database;
import com.example.pokemon_datablind.databinding.ListPokemonRecyclerViewBinding;

import com.bumptech.glide.Glide;
import com.example.pokemon_datablind.Model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class RecyclerPokemonAdapter extends RecyclerView.Adapter<RecyclerPokemonAdapter.MyViewHolder> implements Filterable {

    Context context;
    ArrayList<Pokemon> lstPokemon;
    ArrayList<Pokemon> lstPokemon_private;
    public static int soLan = 1;

    public RecyclerPokemonAdapter(Context context, ArrayList<Pokemon> lstPokemon) {
        this.context = context;
        this.lstPokemon = lstPokemon;
        this.lstPokemon_private = lstPokemon;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListPokemonRecyclerViewBinding binding = ListPokemonRecyclerViewBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pokemon pkm = lstPokemon.get(position);
        String idChuanHoa = "#" + setID(pkm.getId());
        holder.binding.Id.setText(idChuanHoa);
        holder.binding.Name.setText(pkm.getName());
        holder.binding.detailBackgroud.setBackgroundColor(Color.parseColor(getColor(pkm.getTypes(0))));

        Glide.with(context).load(pkm.getImage()).into(holder.binding.imagePokemon);
        String tenMaPokemon = pkm.getName() + idChuanHoa;
        if(isDownloaded(tenMaPokemon))
        {
            holder.binding.iconDownload.setImageResource(android.R.color.transparent);
        }

        RecylerTypesAdapter recyclerViewTypes = new RecylerTypesAdapter(pkm.getTypes());
        holder.binding.recyclerViewTypes.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerViewTypes.setAdapter(recyclerViewTypes);

        holder.binding.iconDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hello");
                this.equals("h");
            }
        });
        holder.binding.iconDownload.setOnClickListener(suKien -> {

            if(isDownloaded(tenMaPokemon) == false) {
                soLan++;
                Database database = new Database(context, "pokemondb.sqlite", null, 1);
                String sql = "CREATE TABLE IF NOT EXISTS DownloadedPokemon(id INTEGER PRIMARY KEY AUTOINCREMENT, Pokemon VARCHAR(200))";
                database.queryData(sql);
                String sqlThem = "INSERT INTO DownloadedPokemon VALUES(null, '" + tenMaPokemon + "')";
                database.queryData(sqlThem);
                holder.binding.iconDownload.setImageResource(android.R.color.transparent);
                Toast.makeText(context, "Đã lưu " + pkm.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean isDownloaded(String name)
    {
        List<String> list = new ArrayList<>();
        Database database = new Database(context, "pokemondb.sqlite", null, 1);
        boolean result = false;
        if(soLan == 1) {
            String sql = "CREATE TABLE IF NOT EXISTS DownloadedPokemon(id INTEGER PRIMARY KEY AUTOINCREMENT, Pokemon VARCHAR(200))";
            database.queryData(sql);
            String sqlThem = "INSERT INTO DownloadedPokemon VALUES(null, '" + "bang" + "')";
            database.queryData(sqlThem);
        }
        String select = "select * from DownloadedPokemon";
        Cursor duLieu = database.getData(select);
        while(duLieu.moveToNext())
        {
            String maPo = duLieu.getString(1);
            list.add(maPo);
        }
        //Kiểm tra xem có tên pokemon nào đã tải không
        if(list.size() > 0) {
            for (String pokemon1 : list) {
                if (pokemon1.equals(name)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public String setID(int id){
        String id_num = null;
        if(id < 10)
            id_num = "00" + String.valueOf(id);
        else if (id < 100) {
            id_num = "0" + String.valueOf(id);
        }
        else {
            id_num = String.valueOf(id);
        }
        return  id_num;
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
        return lstPokemon.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if (search.isEmpty()) {
                    lstPokemon = lstPokemon_private;
                } else {
                    ArrayList<Pokemon> filteredList = new ArrayList<>();
                    for (Pokemon pokemon : lstPokemon_private) {
                        if (pokemon.getName().toLowerCase().contains(search.toLowerCase())) {
                            filteredList.add(pokemon);
                        }
                    }
                    lstPokemon = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = lstPokemon;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                lstPokemon = (ArrayList<Pokemon>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ListPokemonRecyclerViewBinding binding;

        public MyViewHolder(ListPokemonRecyclerViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
