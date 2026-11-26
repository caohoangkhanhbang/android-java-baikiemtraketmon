package com.example.pokemon_datablind.Retrofix;

import com.example.pokemon_datablind.Model.Pokemon;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;

public interface MockAPI_Pokemon {
//    Lấy tất cả
    @GET("/pokemon.json")
    Call<ArrayList<Pokemon>> getItem();
//  Lấy theo id
    @GET("/pokemon/{id}")
    Call<Pokemon> getDetailItem(@Part("id") String id);
}
