package jcuratcha.weather.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CurrentWeatherObject {

    private List<WeatherObject> weather;
    double currentTemperature;
    String currentWeatherDescription;

    public CurrentWeatherObject(JSONObject jsonObject) throws JSONException {
        JSONArray weatherArray;
        JSONObject mainWeatherObject;
        double temperature;
        String description;

        if (jsonObject != null) {
            if (jsonObject.has("weather")) {
                weatherArray = jsonObject.getJSONArray("weather");

            }

            if (jsonObject.has("main")) {
                mainWeatherObject = jsonObject.getJSONObject("main");

                currentTemperature = mainWeatherObject.getDouble("temp");
            }
        }
    }

    public double GetTemperature() {
        return currentTemperature;
    }

}
