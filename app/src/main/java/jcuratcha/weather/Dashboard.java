package jcuratcha.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import jcuratcha.weather.WebClients.CurrentWeatherClient;
import jcuratcha.weather.WebClients.CurrentWeatherProvider;

public class Dashboard extends AppCompatActivity {

    private CurrentWeatherProvider currentWeatherProvider;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentWeatherProvider = new CurrentWeatherClient();
        requestQueue = Volley.newRequestQueue(this);

        final TextView mTextView = (TextView) findViewById(R.id.request_text);

        setContentView(R.layout.activity_dashboard);
    }

    public void GetWeather(View view) {
        currentWeatherProvider.GetCurrentWeather(requestQueue, 6183235);
    }
}
