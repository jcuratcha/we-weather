package jcuratcha.weather.WebClients;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import jcuratcha.weather.Objects.CurrentWeatherObject;

/**
 * Created by DoorCrasher on 2016-12-10.
 */

public class CurrentWeatherClient implements CurrentWeatherProvider {

    private String URL = "api.openweathermap.org";
    private String apiKey = "d3cd60bd315a278f3df5b55318c2ca8d";

    JSONObject requestResponse;
    String errorString;

    public CurrentWeatherObject GetCurrentWeather(final Activity activity, int cityId) {

        CurrentWeatherObject currentWeather;

        RequestQueue queue = Volley.newRequestQueue(activity);

        String url = String.format(Locale.CANADA, "http://%1$s/data/2.5/weather?id=%2$d&appid=%3$s", URL, cityId, apiKey);


        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(activity, response.toString(), Toast.LENGTH_SHORT).show();
                requestResponse = response;
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    errorString = "Error, could not get weather details -> " + error;
                }
            });

        queue.add(stringRequest);

        if (errorString != null)
            Log.d("State", errorString);

        try {
            return new CurrentWeatherObject(requestResponse);
        } catch (JSONException e) {
            Log.e("ERROR - JSON", "Could not extract data from json response");
            return null;
        }
    }
}
