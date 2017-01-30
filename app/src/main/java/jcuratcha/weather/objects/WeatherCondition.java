package jcuratcha.weather.objects;

public enum WeatherCondition {
    CLEAR_SKY, FEW_CLOUDS, SCATTERED_CLOUDS,
    BROKEN_CLOUDS, SHOWER_RAIN, RAIN,
    THUNDERSTORMS, SNOW, MIST;

    public WeatherCondition getIconForCondition(String code) {
        return null; //TODO: plan out condition codes
    }
}

