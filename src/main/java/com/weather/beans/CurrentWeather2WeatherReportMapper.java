package com.weather.beans;

import net.aksingh.owmjapis.CurrentWeather;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CurrentWeather2WeatherReportMapper {

    public Optional<WeatherReport> map(CurrentWeather cwd)  {
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setId(cwd.getCityCode());
        weatherReport.setCityName(cwd.getCityName());
        weatherReport.setWeatherDescription(cwd.getWeatherInstance(0).getWeatherDescription());
        weatherReport.setTemperatureInFahrenheit(Double.valueOf(cwd.getMainInstance().getTemperature()));
        weatherReport.setDateToday(cwd.getDateTime());
        weatherReport.setTimeAtSunrise(cwd.getSysInstance().getSunriseTime());
        weatherReport.setTimeAtSunset(cwd.getSysInstance().getSunsetTime());
        return Optional.of(weatherReport);
    }
}

