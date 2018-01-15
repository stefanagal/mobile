package com.example.vladl.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;



public class DetailsActivity extends AppCompatActivity {
    private PokemonDatabase database;
    String baseUrl = "http://10.0.2.2:3000/";
    String url;
    RequestQueue requestQueue;

    public void updatePokemon(int id, String name, String type, String role)
    {
        database = PokemonDatabase.getDatabase(getApplicationContext());
        this.url = this.baseUrl + "pokemon/" + Integer.toString(id);
        String jsonBody = "{\"id\":" + Integer.toString(id) + ",\"name\":\"" + name + "\",\"type\":\"" + type + "\",\"role\":\""+role+"\"}";
        Log.v("dumnezocumila", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + jsonBody);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(jsonBody);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.v("URL", "------------------------------------" + url);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.PUT, url,jsonObject,
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

    public void deletePokemon(int id)
    {
        database = PokemonDatabase.getDatabase(getApplicationContext());
        this.url = this.baseUrl + "pokemon/" + Integer.toString(id);
        Log.v("URL", "------------------------------------" + url);
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.DELETE, url,
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        database = PokemonDatabase.getDatabase(getApplicationContext());

        requestQueue = Volley.newRequestQueue(this);

        final int index = getIntent().getIntExtra("index", -1);
        final int pokemonId = database.PokemonDao().getEntries().get(index).getId();
        final EditText pokemonName = (EditText)findViewById(R.id.pokemonNameEditText);
        final EditText pokemonType = (EditText)findViewById(R.id.pokemonTypeEditText);
        final EditText pokemonRole = (EditText)findViewById(R.id.pokemonRoleEditText);

        pokemonName.setText(database.PokemonDao().getEntries().get(index).getName());
        pokemonType.setText(database.PokemonDao().getEntries().get(index).getType());
        pokemonRole.setText(database.PokemonDao().getEntries().get(index).getRole());

        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pokemon crtPokemon = new Pokemon(pokemonName.getText().toString(), pokemonType.getText().toString(),pokemonRole.getText().toString());
                crtPokemon.setId(pokemonId);
                updatePokemon(index+1, crtPokemon.getName(), crtPokemon.getType(), crtPokemon.getRole());
                database.PokemonDao().update(crtPokemon);
                finish();
            }
        });

        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Instantiate an AlertDialog.Builder with its constructor

                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage("Are you sure you want to delete this?")
                        .setTitle("Warning");

                // Add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Pokemon crtPokemon = database.PokemonDao().getEntries().get(index);
                        deletePokemon(index + 1);
                        database.PokemonDao().delete(crtPokemon);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        final NumberPicker picker = (NumberPicker) findViewById(R.id.np);
        picker.setMinValue(0);
        picker.setMaxValue(2);
        final String[] pickervalues=new String[] { "Battle", "Watch", "Trial" };
        picker.setDisplayedValues(pickervalues);
        picker.setWrapSelectorWheel(true);

        Button shareButton = (Button) findViewById(R.id.shareButton);
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "galstefana@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, pokemonName.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, pokemonType.getText().toString() + " " + pokemonRole.getText().toString() + " " + picker.toString());
                startActivity(Intent.createChooser(intent, "Send Email"));

            }
        });

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 2));
        entries.add(new BarEntry(2, 5));
        entries.add(new BarEntry(3, 7));
        BarDataSet dataset = new BarDataSet(entries, "Pokemon used by types");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        BarChart chart = (BarChart)findViewById(R.id.chartView);
        BarData data = new BarData(dataset);
        Legend l = chart.getLegend();
        l.setExtra(ColorTemplate.COLORFUL_COLORS, new String[]{"poison", "grass", "water"});
        chart.setData(data);
    }
}
