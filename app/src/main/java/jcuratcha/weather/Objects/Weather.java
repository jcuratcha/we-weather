package jcuratcha.weather.objects;

public class Weather {
    private int id;
    private String main;
    private WeatherCondition description;
    private String weatherIcon;

    public Weather(int newId,
                   String newMain,
                   String newDescription,
                   String newWeatherIcon) {
        id = newId;
        main = newMain;
        description = WeatherCondition.valueOf(newDescription); //TODO: this will fail, need to figure out how to handle this
        weatherIcon = newWeatherIcon;
    }
}
