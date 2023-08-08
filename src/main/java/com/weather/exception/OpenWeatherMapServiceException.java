package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class OpenWeatherMapServiceException extends RuntimeException {
    public OpenWeatherMapServiceException(String message) {
        super(message);
    }
}