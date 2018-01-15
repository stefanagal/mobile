package com.example.vladl.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonListActivity extends AppCompatActivity {

    static PokemonController pokemonController = new PokemonController();
    ArrayAdapter<Pokemon> listAdapter;
    private PokemonDatabase database;
    //private AppController appController = new AppController();
    String baseUrl = "http://10.0.2.2:3000/";
    String url;
    RequestQueue requestQueue;
    Boolean isLoading = false;

    public static PokemonController getPokemonController() {
        return pokemonController;
    }
/*
    public void getPokemon()
    {
        database = PokemonDatabase.getDatabase(getApplicationContext());
        database.PokemonDao().nukeAll();
        this.url = this.baseUrl + "pokemon";
        Log.v("URL", "------------------------------------" + url);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {

                            // The user does have repos, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each repo, add a new line to our repo list.
                                    JSONObject jsonObj = response.getJSONObject(i);

                                    String name = jsonObj.get("name").toString();
                                    String type = jsonObj.get("type").toString();
                                    String role = jsonObj.get("role").toString();
                                    database.PokemonDao().insert(new Pokemon(name, type, role));
                                    Log.v("onResponse", "--------------------------------------------------------------------------");
                                    Log.v("onResponse",name + " " + type + " " + role);

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                            isLoading = false;
                        } else {
                            // The user didn't have any repos.
                            //setRepoListText("No repos found.");
                            Log.v("nu", "nu-i bine");
                        }

                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", "Naspa");
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        database = PokemonDatabase.getDatabase(getApplicationContext());


        //---------------------------VOLLEY STUFF----------------------
        isLoading = true;
        //requestQueue = Volley.newRequestQueue(this);

        //getPokemon();
        if(isLoading){


        }
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
