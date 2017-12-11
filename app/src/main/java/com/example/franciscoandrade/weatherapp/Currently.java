package com.example.franciscoandrade.weatherapp;

/**
 * Created by franciscoandrade on 12/11/17.
 */

public class Currently {


    private int time;
    private String summary;
    private String icon;
    private int nearestStormDistance;
    private int nearestStormBearing;
    private int precipIntensity;
    private int precipProbability;
    private double temperature;
    private double apparentTemperature;
    private double dewPoint;
    private double humidity;
    private double pressure;
    private double windSpeed;
    private double windGust;
    private int windBearing;
    private double cloudCover;
    private int uvIndex;
    private int visibility;
    private double ozone;


    public int getTime() {
        return time;
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }

    public int getNearestStormDistance() {
        return nearestStormDistance;
    }

    public int getNearestStormBearing() {
        return nearestStormBearing;
    }

    public int getPrecipIntensity() {
        return precipIntensity;
    }

    public int getPrecipProbability() {
        return precipProbability;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindGust() {
        return windGust;
    }

    public int getWindBearing() {
        return windBearing;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getVisibility() {
        return visibility;
    }

    public double getOzone() {
        return ozone;
    }
}
