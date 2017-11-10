package com.example.vladl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final int index = getIntent().getIntExtra("index", -1);
        final EditText pokemonName = (EditText)findViewById(R.id.pokemonNameEditText);
        final EditText pokemonType = (EditText)findViewById(R.id.pokemonTypeEditText);
        final EditText pokemonRole = (EditText)findViewById(R.id.pokemonRoleEditText);

        pokemonName.setText(PokemonListActivity.getPokemonController().getPokemonArrayList().get(index).getName());
        pokemonType.setText(PokemonListActivity.getPokemonController().getPokemonArrayList().get(index).getType());
        pokemonRole.setText(PokemonListActivity.getPokemonController().getPokemonArrayList().get(index).getRole());

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PokemonListActivity.getPokemonController().update(
                        index, pokemonName.getText().toString(), pokemonType.getText().toString(),pokemonRole.getText().toString());
            }
        });

        Button shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "galstefana@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, pokemonName.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, pokemonType.getText().toString() + " " + pokemonRole.getText().toString());
                startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });
    }
}
