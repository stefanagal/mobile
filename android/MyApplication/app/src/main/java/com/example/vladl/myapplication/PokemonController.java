package com.example.vladl.myapplication;

import java.util.ArrayList;

/**
 * Created by vladl on 09/11/2017.
 */

public class PokemonController {
    ArrayList<Pokemon> pokemonArrayList;

    public PokemonController() {
        this.pokemonArrayList = new ArrayList<>();
        pokemonArrayList.add(new Pokemon("bulbasaur", "grass/poison", "stall"));
        pokemonArrayList.add(new Pokemon("squirtle", "water", "special attacker"));
    }

    public ArrayList<Pokemon> getPokemonArrayList() {
        return pokemonArrayList;
    }

    public void update(int index, String name, String type, String role){
        pokemonArrayList.get(index).setName(name);
        pokemonArrayList.get(index).setRole(role);
        pokemonArrayList.get(index).setType(type);
    }
}
