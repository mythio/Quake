package com.example.mythio.quake;

import android.nfc.Tag;
import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private ArrayList<Earthquake> earthquakes;
    private RequestQueue mRequestQueue;
    private RecyclerView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        earthquakes = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        earthquakeListView = findViewById(R.id.list);
        earthquakeListView.setHasFixedSize(true);
        earthquakeListView.setLayoutManager(new LinearLayoutManager(this));

        parse();
    }

    private void parse() {
        String url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2018-12-10&endtime=2018-12-20&minmagnitude=4.5";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("features");

                            for (int i = 0; i < jsonArray.length(); ++i) {
                                JSONObject object = jsonArray.getJSONObject(i).getJSONObject("properties");

                                String place = object.getString("place");
                                String parts[];
                                String locationOffset;
                                String location;

                                if (place.contains(" of ")) {
                                    parts = place.split(" of ");
                                    locationOffset = parts[0] + " of";
                                    location = parts[1];
                                } else {
                                    locationOffset = "Near the";
                                    location = place;
                                }

                                Date date = new Date(Long.valueOf(object.getString("time")));
                                SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
                                SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
                                String dateVal = dateFormat.format(date);
                                String timeVal = timeFormat.format(date);

                                earthquakes.add(new Earthquake(
                                        String.valueOf(object.getDouble("mag")),
                                        locationOffset,
                                        location,
                                        dateVal,
                                        timeVal
                                ));
                            }
                            EarthquakeAdapter adapter = new EarthquakeAdapter(getApplicationContext(), earthquakes);
                            earthquakeListView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, error.getMessage());
            }
        });
        mRequestQueue.add(jsonObjectRequest);
    }
}
