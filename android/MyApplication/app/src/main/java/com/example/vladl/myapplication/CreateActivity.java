package com.example.vladl.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateActivity extends AppCompatActivity {

    private PokemonDatabase database;
    String baseUrl = "http://10.0.2.2:3000/";
    String url;
    RequestQueue requestQueue;

    public void addPokemon(int id, String name, String type, String role)
    {
        database = PokemonDatabase.getDatabase(getApplicationContext());
        this.url = this.baseUrl + "pokemon";
        String jsonBody = "{\"id\":" + Integer.toString(id) + ",\"name\":\"" + name + "\",\"type\":\"" + type + "\",\"role\":\""+role+"\"}";
        Log.v("dumnezocumila", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + jsonBody);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(jsonBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("URL", "------------------------------------" + url);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.POST, url,jsonObject,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final PokemonDatabase database = PokemonDatabase.getDatabase(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        requestQueue = Volley.newRequestQueue(this);
        final EditText pokemonName = (EditText) findViewById(R.id.pokemonNameEditTextC);
        final EditText pokemonType = (EditText) findViewById(R.id.pokemonTypeEditTextC);
        final EditText pokemonRole = (EditText) findViewById(R.id.pokemonRoleEditTextC);

        Button saveButton = (Button) findViewById(R.id.saveButtonC);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pokemon crtPokemon = new Pokemon(pokemonName.getText().toString(), pokemonType.getText().toString(), pokemonRole.getText().toString());
                addPokemon(crtPokemon.getId(), crtPokemon.getName(), crtPokemon.getType(), crtPokemon.getRole());
                database.PokemonDao().insert(crtPokemon);
                finish();
            }
        });
    }
}
