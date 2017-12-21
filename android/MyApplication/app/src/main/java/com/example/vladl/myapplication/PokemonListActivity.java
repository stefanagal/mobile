package com.example.vladl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class PokemonListActivity extends AppCompatActivity {

    static PokemonController pokemonController = new PokemonController();
    ArrayAdapter<Pokemon> listAdapter;
    private PokemonDatabase database;

    public static PokemonController getPokemonController() {
        return pokemonController;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        database = PokemonDatabase.getDatabase(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.pokemonListView);

        listAdapter = new ArrayAdapter<Pokemon>(this, android.R.layout.simple_list_item_1, database.PokemonDao().getEntries());
        listView.setAdapter(listAdapter);

        final Intent detailsIntent = new Intent(this, DetailsActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detailsIntent.putExtra("index", position);
                startActivity(detailsIntent);
            }
        });

        final Intent createIntent = new Intent(this, CreateActivity.class);
        Button createButton = (Button) findViewById(R.id.addListButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(createIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.clear();
        listAdapter.addAll(database.PokemonDao().getEntries());
        listAdapter.notifyDataSetChanged();
    }
}
