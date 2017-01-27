package jcuratcha.weather.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeatherTest {

    int id = 1;
    String main = "cloud";
    String description = "so cloudy";
    double windSpeed = 9000;

    @Test
    public void negative_value_should_return_direction() {
        double angle = 300;

        Weather weather = new Weather(id, main, description, null, windSpeed, angle);

        assertEquals("Should have converted to correct CardinalDirection",
                Weather.CardinalDirection.NORTH_WEST,
                weather.getWindDirection());
    }
}
