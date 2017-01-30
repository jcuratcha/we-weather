package jcuratcha.weather.objects;

public class Wind {

    double speed;
    double direction;
    String directionName;

    public Wind (double speed, double direction) {
        this.speed = speed;
        this.direction = direction;
        this.directionName = getDirectionNameFromAngle(direction);
    }

    private String getDirectionNameFromAngle(double angle) {
        return null; //TODO: convert angles to actual cardinal directions
    }
}
