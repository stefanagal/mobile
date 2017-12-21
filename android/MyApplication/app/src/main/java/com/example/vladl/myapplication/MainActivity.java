package com.example.vladl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pokemonListButton = (Button) findViewById(R.id.pokemonListButton);
        final Intent pokemonListIntent = new Intent(this, PokemonListActivity.class);

        pokemonListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pokemonListIntent);
            }
        });
        PokemonDatabase database = PokemonDatabase.getDatabase(getApplicationContext());
        //database.PokemonDao().insert(new Pokemon("a", "grass/poison", "stall"));
        //database.PokemonDao().insert(new Pokemon("b", "water", "special attacker"));

    }

}
