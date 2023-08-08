package com.weather.controller;

import com.weather.beans.WeatherReport;
import com.weather.exception.OpenWeatherMapServiceException;
import com.weather.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private IWeatherService IWeatherService;

    @Autowired
    public WeatherController(IWeatherService IWeatherService) {
        this.IWeatherService = IWeatherService;
    }

    @RequestMapping(value = "/current/{cityCode}")
    public WeatherReport getCurrentWeatherForCity(@PathVariable(value = "cityCode") String cityCode)
            throws OpenWeatherMapServiceException {
        return IWeatherService.getCurrentWeatherForCity(cityCode).orElseThrow(() -> new OpenWeatherMapServiceException("Weather data unavailable"));
    }

}
