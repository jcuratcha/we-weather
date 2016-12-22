package jcuratcha.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Locale;

import jcuratcha.weather.WebClients.CurrentWeatherClient;
import jcuratcha.weather.WebClients.CurrentWeatherProvider;

public class Dashboard extends AppCompatActivity {

    private CurrentWeatherProvider currentWeatherProvider;

    RequestQueueSingleton requestHelper = RequestQueueSingleton.getInstance();
    TextView mTextDegrees, mTextWeather, mTextError;

    private String URL = "api.openweathermap.org";
    private String apiKey = "d3cd60bd315a278f3df5b55318c2ca8d";
    private int cityId = 6183235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        currentWeatherProvider = new CurrentWeatherClient();

        mTextDegrees = (TextView) findViewById(R.id.text_degrees);
        mTextWeather = (TextView) findViewById(R.id.text_current_weather);
        mTextError = (TextView) findViewById(R.id.text_error);

    }

    public void GetCurrentWeatherData(View view) {

        String url = String.format(
                Locale.CANADA,
                "http://%1$s/data/2.5/weather?id=%2$d&appid=%3$s",
                URL,
                cityId,
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
