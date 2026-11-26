package com.example.pokemon_datablind.Model;

import java.util.List;

public class Pokemon {

    protected int id;
    protected String name, image;
    protected List<String> types;

//    public Pokemon(int id, String name, String image, ArrayList<String> types) {
//        this.id = id;
//        this.name = name;
//        this.image = image;
//        this.types = types;
//    }

    public Pokemon()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTypes() {
        return types;
    }
    public String getTypes(int q) {
        String type = types.get(q);
        return type;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }


}
