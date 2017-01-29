package jcuratcha.weather.objects;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WeatherTest {

    int id = 1;
    String main = "cloud";
    String description = "so cloudy";
    double windSpeed = 9000;

    Weather weather;

    @Before
    public void setUp() {
        weather = new Weather();
    }

    @Test
    public void getting_wind_direction_should_return_correct_direction(){

        Map<Double, Weather.CardinalDirection> angles = new HashMap<Double, Weather.CardinalDirection>() {{
            put(10.1, Weather.CardinalDirection.NORTH);
            put(145.36, Weather.CardinalDirection.SOUTH_EAST);
            put(188d, Weather.CardinalDirection.SOUTH);
            put(704.90, Weather.CardinalDirection.NORTH_NORTH_WEST);
            put(1080.87, Weather.CardinalDirection.NORTH);
            put(80d, Weather.CardinalDirection.EAST);
            put(260d, Weather.CardinalDirection.WEST);
            put(-352.2, Weather.CardinalDirection.NORTH);
            put(-135.0, Weather.CardinalDirection.SOUTH_WEST);
            put(-180.0, Weather.CardinalDirection.SOUTH);
            put(-277.4392, Weather.CardinalDirection.EAST);
            put(-123.4, Weather.CardinalDirection.WEST_SOUTH_WEST);
        }};

        Set<Double> keys = angles.keySet();

        for (double angle : keys) {
            weather.setWindDirection(angle);

            assertEquals("Should have converted to correct CardinalDirection",
                    angles.get(angle),
                    weather.getWindDirection());
        }
    }
}
