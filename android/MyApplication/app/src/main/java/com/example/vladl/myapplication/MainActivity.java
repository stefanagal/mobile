package com.example.vladl.myapplication;

import android.app.Activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private PokemonDatabase database;
    //private AppController appController = new AppController();
    String baseUrl = "http://10.0.2.2:3000/";
    String url;
    RequestQueue requestQueue;
    Boolean isLoggedIn = false;

    public void notification()
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Sets an ID for the notification
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }

    public void authenticateUser(String username, String password)
    {
        this.url = this.baseUrl + "users?username=" + username + "&password=" + password;
        Log.v("URL", "------------------------------------" + url);


        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>(){

                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            isLoggedIn = true;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification();

        requestQueue = Volley.newRequestQueue(this);

        getPokemon();

        final EditText username = (EditText) findViewById(R.id.loginUsernameEditText);
        final EditText password = (EditText) findViewById(R.id.loginPasswordEditText);

        final Button pokemonListButton = (Button) findViewById(R.id.pokemonListButton);
        final Intent pokemonListIntent = new Intent(this, PokemonListActivity.class);

        pokemonListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser(username.getText().toString(), password.getText().toString());
                if(isLoggedIn)
                    startActivity(pokemonListIntent);

            }
        });

        PokemonDatabase database = PokemonDatabase.getDatabase(getApplicationContext());
        //database.PokemonDao().insert(new Pokemon("a", "grass/poison", "stall"));
        //database.PokemonDao().insert(new Pokemon("b", "water", "special attacker"));

    }

}
