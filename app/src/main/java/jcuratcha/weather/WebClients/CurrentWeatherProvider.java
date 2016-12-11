package jcuratcha.weather.WebClients;

import jcuratcha.weather.Objects.CurrentWeatherObject;

/**
 * Created by DoorCrasher on 2016-12-11.
 */

public interface CurrentWeatherProvider {

    CurrentWeatherObject GetCurrentWeather(int cityId);
}
