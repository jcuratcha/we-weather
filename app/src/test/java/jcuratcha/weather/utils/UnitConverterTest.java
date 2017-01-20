package jcuratcha.weather.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnitConverterTest {

    private final double TEMP_DELTA = 1;

    @Test
    public void valid_K_should_convert_to_F() {
        int kelvin = 300;
        int expected = 80;

        assertEquals("Both temperature values should be the same",
                expected,
                UnitConverter.convertKelvinToFahrenheit(kelvin),
                TEMP_DELTA);
    }

    @Test
    public void valid_K_should_convert_to_C() {
        int kelvin = 300;
        int expected = 27;

        assertEquals("Both temperature values should be the same",
                expected,
                UnitConverter.convertKelvinToCelsius(kelvin),
                TEMP_DELTA);
    }
}
