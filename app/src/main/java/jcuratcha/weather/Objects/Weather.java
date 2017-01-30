package jcuratcha.weather.objects;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.Date;

public class Weather extends BaseObservable {

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
    private int temperature;

    // based on intervals from
    // http://climate.umn.edu/snow_fence/components/winddirectionanddegreeswithouttable3.htm
    private final double ANGLE_OFFSET = 11.25;
    private final double DIRECTION_INTERVAL_SIZE = 22.5;

    public long getWeatherId() {
        return id;
    }

    @Bindable
    public String getMain() {
        return main;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    @Bindable
    public String getWeatherIcon() {
        return weatherIcon;
    }

    @Bindable
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Calculates the {@link CardinalDirection} based on the angle of the wind
     * @return a {@link CardinalDirection} representing the current wind direction
     */
    @Bindable
    public CardinalDirection getWindDirection() {
        CardinalDirection[] directions = CardinalDirection.values();
        // Java's % operator actually returns the remainder of a division, not the modulus between them
        // Here I need the modulus. There is a floorMod(int x, int y) method in Java 8/API level 24 that
        // has this method, so this should be taken into consideration when updating the project.
        // source: http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
        int index = (int)Math.floor(((((windDirection + ANGLE_OFFSET % 360) + 360) % 360))/DIRECTION_INTERVAL_SIZE);

        return directions[index];
    }

    @Bindable
    public int getHumidity() {
        return humidity;
    }

    @Bindable
    public int getPressure() {
        return pressure;
    }

    @Bindable
    public int getCloudiness() {
        return cloudiness;
    }

    @Bindable
    public Date getLastUpdated() {
        return lastUpdated;
    }

    @Bindable
    public int getTemperature() {
        return temperature;
    }

    public Weather() {
        id = 0;
        main = null;
        description = null;
        weatherIcon = null;

        windSpeed = 0;
        windDirection = 0;

        humidity = 0;
        pressure = 0;
        cloudiness = 0;
        lastUpdated = null;
        temperature = 0;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
        notifyPropertyChanged(BR.main);
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
        notifyPropertyChanged(BR.weatherIcon);
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        notifyPropertyChanged(BR.windSpeed);
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
        notifyPropertyChanged(BR.windDirection);
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
        notifyPropertyChanged(BR.humidity);
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
        notifyPropertyChanged(BR.pressure);
    }

    public void setCloudiness(int cloudiness) {
        this.cloudiness = cloudiness;
        notifyPropertyChanged(BR.cloudiness);
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
        notifyPropertyChanged(BR.lastUpdated);
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
        notifyPropertyChanged(BR.temperature);
    }
}
