package jcuratcha.weather.objects;

/**
 * Created by DoorCrasher on 2016-12-11.
 */

public class WeatherObject {
    private int id;
    private String main;
    private String description;
    private String weatherIcon;

    public WeatherObject(int newId,
                         String newMain,
                         String newDescription,
                         String newWeatherIcon) {
        id = newId;
        main = newMain;
        description = newDescription;
        weatherIcon = newWeatherIcon;
    }
}
