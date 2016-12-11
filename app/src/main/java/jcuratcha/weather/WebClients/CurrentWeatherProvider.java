package jcuratcha.weather.WebClients;

import com.android.volley.RequestQueue;

import jcuratcha.weather.Objects.CurrentWeatherObject;

/**
 * Created by DoorCrasher on 2016-12-11.
 */

public interface CurrentWeatherProvider {

    CurrentWeatherObject GetCurrentWeather(RequestQueue requestQueue, int cityId);
}
