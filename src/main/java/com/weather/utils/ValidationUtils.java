package com.weather.utils;

import net.aksingh.owmjapis.CurrentWeather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtils.class);

    public boolean isValidCityCode(String cityCode) {
        try {
            Long.valueOf(cityCode);
        } catch (NumberFormatException ex) {
            LOGGER.info("Invalid city code provided");
            return false;
        }
        LOGGER.info("Valid city code provided");
        return true;
    }

    public boolean isValidWeatherResponse(CurrentWeather cwd) {
        if (!cwd.isValid() || !cwd.hasCityName()
                || !cwd.hasDateTime() || !cwd.hasWeatherInstance()
                || !cwd.hasSysInstance() || !cwd.hasMainInstance()) {
            LOGGER.info("Invalid weather response");
            if (cwd.hasResponseCode()) {
                LOGGER.info("Response code:" + cwd.getResponseCode());
            }
            return false;
        }
        LOGGER.info("Valid weather response received");
        return true;
    }
}
