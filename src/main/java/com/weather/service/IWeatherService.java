package com.weather.service;

import com.weather.beans.WeatherReport;
import java.util.Optional;

public interface IWeatherService {
    Optional<WeatherReport> getCurrentWeatherForCity(String cityCode);
}
