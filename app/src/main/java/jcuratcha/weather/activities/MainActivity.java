package jcuratcha.weather.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Locale;

import jcuratcha.weather.R;
import jcuratcha.weather.network.VolleyRequestQueue;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    VolleyRequestQueue requestHelper;
    TextView mTextDegrees, mTextWeather, mTextError;
    EditText mCityNameTextInput;

    private String URL = "api.openweathermap.org";
    private String apiKey = "d3cd60bd315a278f3df5b55318c2ca8d";
    private int cityId = 6183235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestHelper = VolleyRequestQueue.getInstance(this);

        mTextDegrees = (TextView) findViewById(R.id.text_degrees);
        mTextWeather = (TextView) findViewById(R.id.text_current_weather);
        mTextError = (TextView) findViewById(R.id.text_error);

        mCityNameTextInput = (EditText) findViewById(R.id.city_name_edit_text);

        prefs = getSharedPreferences("com.doorcrasher.WeWeather", MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstRun", true)) {

            Intent intent = new Intent(this, FirstTimeSetupActivity.class);
            startActivity(intent);

            prefs.edit().putBoolean("firstRun", false).apply();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, UserSettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void GetCurrentWeatherData(View view) {

//        String cityName = mCityNameTextInput.getText().toString().replaceAll("\\s+","");
        String cityName = prefs.getString("currentLocation", null).replaceAll("\\s+","");

//        String url = String.format(
//                Locale.CANADA,
//                "http://%1$s/data/2.5/weather?id=%2$d&appid=%3$s",
//                URL,
//                cityId,
//                apiKey
//        );

        String url = String.format(
                Locale.CANADA,
                "http://%1$s/data/2.5/weather?q=%2$s&appid=%3$s",
                URL,
                cityName,
                apiKey
        );

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    double currentTemp;
                    String currentWeatherCondition;

                    currentWeatherCondition = response.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("description");

                    currentTemp = response.getJSONObject("main")
                            .getDouble("temp");

                    currentTemp = convertKelvinToCelcius(currentTemp);

                    mTextWeather.setText(currentWeatherCondition);
                    mTextDegrees.setText(String.valueOf(currentTemp));

                } catch (Exception e) {
                    updateTextError(e);
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                updateTextError(error);
            }
        });

        requestHelper.add(jsonRequest);
    }

    private void updateTextError(Exception e) {
        mTextError.setVisibility(View.VISIBLE);
        e.printStackTrace();
    }

    private double convertKelvinToCelcius(double tempKelvin) {
        return (tempKelvin - 273.15);
    }
}
