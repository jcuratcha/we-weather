package jcuratcha.weather.objects;

import java.util.Date;

public class Weather {

    private long id;    // The ID for the current location (i.e. Winnipeg is 6183235
    private String main;        // Main condition
    private String description; // Description of current condition
    private String weatherIcon; // ID for icon of current condition
    private int windSpeed;
    private double windDirection;
    private int humidity;
    private int pressure;
    private int cloudiness;
    private Date lastUpdated;
    private int temperature;

    // based on intervals from
    // http://climate.umn.edu/snow_fence/components/winddirectionanddegreeswithouttable3.htm
    private final double ANGLE_OFFSET = 11.25;
    private final double DIRECTION_INTERVAL_SIZE = 22.5;

    public long getWeatherId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    /**
     * Calculates the {@link CardinalDirection} based on the angle of the wind
     * @return a {@link CardinalDirection} representing the current wind direction
     */
    public CardinalDirection getWindDirection() {
        CardinalDirection[] directions = CardinalDirection.values();
        // Java's % operator actually returns the remainder of a division, not the modulus between them
        // Here I need the modulus. There is a floorMod(int x, int y) method in Java 8/API level 24 that
        // has this method, so this should be taken into consideration when updating the project.
        // source: http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
        int index = (int)Math.floor(((((windDirection + ANGLE_OFFSET % 360) + 360) % 360))/DIRECTION_INTERVAL_SIZE);

        return directions[index];
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public int getTemperature() {
        return temperature;
    }

    public Weather() {
        return new Weather(0, null, null, null, 0d, 0d, 0,0,0, null, 0d);
    }

    public Weather(long newId,
                   String newMain,
                   String newDescription,
                   String newWeatherIcon,
                   double newWindSpeed,
                   double newWindDirection,
                   int newHumidity,
                   int newPressure,
                   int newCloudiness,
                   Date newLastUpdated,
                   double newTemperature) {

        id = newId;
        main = newMain;
        description = newDescription; //TODO: this will fail, need to figure out how to handle this
        weatherIcon = newWeatherIcon;

        windSpeed = (int)Math.round(newWindSpeed);
        windDirection = newWindDirection;

        humidity = newHumidity;
        pressure = newPressure;
        cloudiness = newCloudiness;
        lastUpdated = newLastUpdated;
        temperature = (int)Math.round(newTemperature);
    }

    public enum CardinalDirection {
        NORTH, NORTH_NORTH_EAST, NORTH_EAST, EAST_NORTH_EAST,
        EAST, EAST_SOUTH_EAST, SOUTH_EAST, SOUTH_SOUTH_EAST,
        SOUTH, SOUTH_SOUTH_WEST, SOUTH_WEST, WEST_SOUTH_WEST,
        WEST, WEST_NORTH_WEST, NORTH_WEST, NORTH_NORTH_WEST
    }
}
