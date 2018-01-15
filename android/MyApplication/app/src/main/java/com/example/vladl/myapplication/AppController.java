package com.example.vladl.myapplication;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vladl on 14/01/2018.
 */

public class AppController extends Application {
    String baseUrl = "http://10.0.2.2:3000/";
    String url;
    RequestQueue requestQueue;

    public AppController() {
        //requestQueue = Volley.newRequestQueue(this);
    }

    public void getPokemon()
    {
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
                                    Log.v("onResponse", "--------------------------------------------------------------------------");
                                    //Log.v("onResponse",name + " " + type + " " + role);
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
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);

    }
}
