package jcuratcha.weather.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
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

import java.util.Date;
import java.util.Locale;

import jcuratcha.weather.R;
import jcuratcha.weather.databinding.ActivityMainBinding;
import jcuratcha.weather.network.VolleyRequestQueue;
import jcuratcha.weather.objects.Weather;
import jcuratcha.weather.utils.UnitConverter;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs = null;

    Weather weather;

    VolleyRequestQueue requestHelper;
    TextView mTextError;

    private String URL = "api.openweathermap.org";
    private String apiKey = "d3cd60bd315a278f3df5b55318c2ca8d";
    private int cityId = 6183235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        weather = new Weather();
        binding.setWeather(weather);

        requestHelper = VolleyRequestQueue.getInstance(this);

        mTextError = (TextView) findViewById(R.id.text_error);

        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
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
        } else if (id == R.id.action_debug_prefs){
            Intent intent = new Intent(MainActivity.this, DebugInfoActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void GetCurrentWeatherData(View view) {

        String cityName = prefs.getString(getString(R.string.key_city_name), null);
        final char temperatureUnit = prefs.getString(getString(R.string.key_temperature_unit), null).charAt(0);

        if (cityName != null)
            cityName.replaceAll("\\s+","");

        String url = String.format(
                Locale.CANADA,
                "http://%1$s/data/2.5/weather?q=%2$s&appid=%3$s",
                URL,
                cityName,
                apiKey
        );

        System.out.println("Current request: [" + url + "]");

        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    weather.setDescription(response.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("description"));


                    weather.setWindSpeed(response.getJSONObject("wind")
                            .getDouble("speed"));

                    weather.setWindDirection(response.getJSONObject("wind")
                            .getDouble("deg"));

                    weather.setHumidity(response.getJSONObject("main")
                            .getInt("humidity"));

                    weather.setPressure(response.getJSONObject("main")
                            .getInt("pressure"));

                    weather.setCloudiness(response.getJSONObject("clouds")
                            .getInt("all"));

                    double currentTemp = response.getJSONObject("main")
                            .getDouble("temp");

                    weather.setLastUpdated(new Date(response.getLong("dt")));

                    switch(temperatureUnit){
                        case 'F':
                            currentTemp = UnitConverter.convertKelvinToFahrenheit(currentTemp);
                            break;
                        case 'C':
                            currentTemp = UnitConverter.convertKelvinToCelsius(currentTemp);
                            break;
                    }

                    weather.setTemperature((int)currentTemp);

//                    weather = new Weather(1, null, description, null, windSpeed,
//                            windDirection, humidity, pressure, cloudPercent,
//                            lastUpdated, currentTemp);

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
}
