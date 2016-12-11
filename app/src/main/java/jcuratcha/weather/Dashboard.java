package jcuratcha.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Locale;

import jcuratcha.weather.WebClients.CurrentWeatherClient;
import jcuratcha.weather.WebClients.CurrentWeatherProvider;

public class Dashboard extends AppCompatActivity {

    private CurrentWeatherProvider currentWeatherProvider;

    private String URL = "api.openweathermap.org";
    private int cityId = 6183235;
    private String apiKey = "d3cd60bd315a278f3df5b55318c2ca8d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentWeatherProvider = new CurrentWeatherClient();

        final TextView mTextView = (TextView) findViewById(R.id.request_text);



        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format(Locale.CANADA, "%1$d/data/2.5/weather?id=%2$d&appid=%3$d", URL, cityId, apiKey);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView.setText("Response is: " + response.substring(0, 500));
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }

        });



        setContentView(R.layout.activity_dashboard);
    }


}
