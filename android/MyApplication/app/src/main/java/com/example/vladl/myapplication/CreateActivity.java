package com.example.vladl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final PokemonDatabase database = PokemonDatabase.getDatabase(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final EditText pokemonName = (EditText) findViewById(R.id.pokemonNameEditTextC);
        final EditText pokemonType = (EditText) findViewById(R.id.pokemonTypeEditTextC);
        final EditText pokemonRole = (EditText) findViewById(R.id.pokemonRoleEditTextC);

        Button saveButton = (Button) findViewById(R.id.saveButtonC);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pokemon crtPokemon = new Pokemon(pokemonName.getText().toString(), pokemonType.getText().toString(), pokemonRole.getText().toString());
                database.PokemonDao().insert(crtPokemon);
                finish();
            }
        });
    }
}
