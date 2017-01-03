package jcuratcha.weather.webclients;

import android.app.Activity;
import android.widget.TextView;

import jcuratcha.weather.objects.CurrentWeatherObject;

/**
 * Created by DoorCrasher on 2016-12-11.
 */

public interface CurrentWeatherProvider {

    CurrentWeatherObject GetCurrentWeather(Activity activity, int cityId, TextView textView);
}
