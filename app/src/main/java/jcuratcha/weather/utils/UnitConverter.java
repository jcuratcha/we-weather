package jcuratcha.weather.utils;

/**
 * Utility class for converting various numbers to different units or scales.
 */

public class UnitConverter {

    public static double convertKelvinToCelsius(double temp) {
        return temp - 273.15;
    }

    public static double convertKelvinToFahrenheit(double temp) {
        return temp * 9 / 5 - 459.67;
    }

    public static double convertKilometresHourToMetresSecond (double speed) {
        return speed / 3.6;
    }

    public static double convertMStoKMH (double speed) {
        return speed * 3.6;
    }
}
