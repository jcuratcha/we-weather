package jcuratcha.weather.objects;

import java.util.Date;

public class Weather {

    private long id;    // The ID for the current location (i.e. Winnipeg is 6183235
    private String main;        // Main condition
    private String description; // Description of current condition
    private String weatherIcon; // ID for icon of current condition
    private double windSpeed;
    private double windDirection;
    private int humidity;
    private int pressure;
    private int cloudiness;
    private Date lastUpdated;

    // based on intervals from
    // http://climate.umn.edu/snow_fence/components/winddirectionanddegreeswithouttable3.htm
    private final double angleOffset = 11.25;

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

    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Calculates the {@link CardinalDirection} based on the angle of the wind
     * @return a {@link CardinalDirection} representing the current wind direction
     */
    public CardinalDirection getWindDirection() {
        CardinalDirection[] directions = CardinalDirection.values();
        // add offset to make mapping easier
        int offsetDirection = (int)Math.round((windDirection + 11.25) % 360);
        int factor = 15;

        // round to nearest multiple of 15
        // https://gist.github.com/aslakhellesoy/1134482
        offsetDirection = ((offsetDirection % factor) > factor/2)
                ? offsetDirection + factor - offsetDirection%factor
                : offsetDirection - offsetDirection%factor;

        int index = Math.round(offsetDirection/22.5f);

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

    public Weather(long newId,
                   String newMain,
                   String newDescription,
                   String newWeatherIcon,
                   double newWindSpeed,
                   double newWindDirection) {
        id = newId;
        main = newMain;
        description = newDescription; //TODO: this will fail, need to figure out how to handle this
        weatherIcon = newWeatherIcon;

        windSpeed = newWindSpeed;
        windDirection = newWindDirection;
    }

    public enum CardinalDirection {
        NORTH, NORTH_NORTH_EAST, NORTH_EAST, EAST_NORTH_EAST,
        EAST, EAST_SOUTH_EAST, SOUTH_EAST, SOUTH_SOUTH_EAST,
        SOUTH, SOUTH_SOUTH_WEST, SOUTH_WEST, WEST_SOUTH_WEST,
        WEST, WEST_NORTH_WEST, NORTH_WEST, NORTH_NORTH_WEST
    }
}
