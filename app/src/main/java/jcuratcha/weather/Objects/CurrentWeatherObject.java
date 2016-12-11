package jcuratcha.weather.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class CurrentWeatherObject {

    private double Longitude;
    private double Latitude;

    private List<WeatherObject> weather;

    public CurrentWeatherObject(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            if (jsonObject.has("lon"))
                Longitude = jsonObject.getDouble("lon");

            if (jsonObject.has("lat"))
                Longitude = jsonObject.getDouble("lat");
        }

    }

}
