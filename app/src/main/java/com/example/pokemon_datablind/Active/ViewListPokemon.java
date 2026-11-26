package com.example.pokemon_datablind.Active;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon_datablind.Adapter.RecyclerPokemonAdapter;
import com.example.pokemon_datablind.Model.Pokemon;
import com.example.pokemon_datablind.R;
import com.example.pokemon_datablind.Retrofix.MockAPI_Pokemon;
import com.example.pokemon_datablind.Retrofix.RetrofixClinet;
import com.example.pokemon_datablind.databinding.ListPokemonBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewListPokemon extends AppCompatActivity {
    ArrayList<Pokemon> lstPokemon = new ArrayList<>();
    RecyclerPokemonAdapter recyclerPokemonAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    private static final String BASE_URL = "https://omo-api.doc.motdev.vn/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListPokemonBinding binding = DataBindingUtil.setContentView(this, R.layout.list_pokemon);
        binding.setLifecycleOwner(this);

        recyclerView = binding.recyclerView;
        searchView = binding.search;

        loadPokemon();
        search();
    }

    private void loadPokemon() {
        MockAPI_Pokemon apiPokemon = RetrofixClinet.getClient(BASE_URL).create(MockAPI_Pokemon.class);
        apiPokemon.getItem().enqueue(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Pokemon>> call, @NonNull Response<ArrayList<Pokemon>> response) {
                lstPokemon = response.body();

                recyclerPokemonAdapter = new RecyclerPokemonAdapter(ViewListPokemon.this, lstPokemon);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewListPokemon.this));
                recyclerView.setAdapter(recyclerPokemonAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<Pokemon>> call, Throwable t) {
                Log.e("Error", "Failed to load data: " + t.getMessage());
            }
        });
    }

    private void search() {
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerPokemonAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerPokemonAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

}
