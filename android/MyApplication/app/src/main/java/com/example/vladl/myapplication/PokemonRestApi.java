package com.example.vladl.myapplication;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by vladl on 14/01/2018.
 */

public class PokemonRestApi
{
    String baseUrl = "http://10.0.2.2:3000/";
    String url;


    public PokemonRestApi()
    {


    }

    public void getPokemon ()
    {
/*
        try {
            URL url = new URL(baseUrl + "pokemon");
            HttpURLConnection myConnection =
                    (HttpURLConnection) url.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");

            myConnection.setRequestProperty("Accept",
                    "application/json");
            Log.v("rest client", url.toString());
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = new BufferedInputStream(myConnection.getInputStream());

                Log.v("rest client", responseBody.toString());
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                JSONArray jsonArray = new JSONArray(responseBodyReader);
                myConnection.disconnect();
                return jsonArray;
            } else {
                Log.v("rest client", "RESPONSE CODE ERROR ---------------------------------------");
                return null;
            }
            //InputStream responseBody = myConnection.getInputStream();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
     */
    }


}
