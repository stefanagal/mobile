package com.example.vladl.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PokemonListActivity extends AppCompatActivity {

    static PokemonController pokemonController = new PokemonController();
    ArrayAdapter<Pokemon> listAdapter;

    public static PokemonController getPokemonController() {
        return pokemonController;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        ListView listView = (ListView) findViewById(R.id.pokemonListView);

        listAdapter = new ArrayAdapter<Pokemon>(this, android.R.layout.simple_list_item_1, pokemonController.getPokemonArrayList());
        listView.setAdapter(listAdapter);

        final Intent detailsIntent = new Intent(this, DetailsActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                detailsIntent.putExtra("index", position);
                startActivity(detailsIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }
}
