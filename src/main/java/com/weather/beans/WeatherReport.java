package com.weather.beans;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class WeatherReport {

    private Long id;
    private String cityName;
    private String weatherDescription;
    private Double temperatureInFahrenheit;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date dateToday;

    @JsonSerialize(using = CustomTimeSerializer.class)
    private Date timeAtSunrise;

    @JsonSerialize(using = CustomTimeSerializer.class)
    private Date timeAtSunset;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Double getTemperatureInCelsius() {
        return (5.0 / 9.0) * (getTemperatureInFahrenheit()  - 32);
    }

    public Double getTemperatureInFahrenheit() {
        return temperatureInFahrenheit;
    }

    public void setTemperatureInFahrenheit(Double temperatureInFahrenheit) {
        this.temperatureInFahrenheit = temperatureInFahrenheit;
    }

    public Date getDateToday() {
        return dateToday;
    }

    public void setDateToday(Date dateToday) {
        this.dateToday = dateToday;
    }

    public Date getTimeAtSunrise() {
        return timeAtSunrise;
    }

    public void setTimeAtSunrise(Date timeAtSunrise) {
        this.timeAtSunrise = timeAtSunrise;
    }

    public Date getTimeAtSunset() {
        return timeAtSunset;
    }

    public void setTimeAtSunset(Date timeAtSunset) {
        this.timeAtSunset = timeAtSunset;
    }

}
