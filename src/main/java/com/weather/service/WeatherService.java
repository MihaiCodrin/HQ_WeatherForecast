package com.weather.service;

import com.weather.beans.CurrentWeather2WeatherReportMapper;
import com.weather.beans.WeatherReport;

import com.weather.exception.InvalidCityCodeException;
import com.weather.utils.ValidationUtils;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WeatherService implements IWeatherService {

    private OpenWeatherMap owm;

    private CurrentWeather2WeatherReportMapper mapper;

    private ValidationUtils validationUtils;

    public WeatherService(String openWeatherMapApiKey, CurrentWeather2WeatherReportMapper mapper, ValidationUtils validationUtils) {
        this.owm = new OpenWeatherMap(openWeatherMapApiKey);
        this.mapper = mapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public Optional<WeatherReport> getCurrentWeatherForCity(String cityCode) {
        if(!validationUtils.isValidCityCode(cityCode)) {
            throw new InvalidCityCodeException();
        }
        CurrentWeather cwm = owm.currentWeatherByCityCode(Long.valueOf(cityCode));
        return (validationUtils.isValidWeatherResponse(cwm)) ? mapper.map(cwm) : Optional.empty();
    }

}