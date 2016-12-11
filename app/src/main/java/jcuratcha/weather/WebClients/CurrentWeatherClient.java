package jcuratcha.weather.WebClients;

import jcuratcha.weather.Objects.CurrentWeatherObject;

/**
 * Created by DoorCrasher on 2016-12-10.
 */

public class CurrentWeatherClient implements CurrentWeatherProvider {

    public CurrentWeatherObject GetCurrentWeather(int cityId) {
        return new CurrentWeatherObject();
    }
}
