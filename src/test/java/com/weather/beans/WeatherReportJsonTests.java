package com.weather.beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherReportJsonTests {

    private JacksonTester<WeatherReport> json;

    private WeatherReport weatherReport;

    @Before
    public void setup() {
        ObjectMapper objectMapper = new ObjectMapper();
        JacksonTester.initFields(this, objectMapper);
        weatherReport = new WeatherReport();
        weatherReport.setId(new Long(1234));
        weatherReport.setCityName("London");
        weatherReport.setWeatherDescription("Sunny with some clouds");
        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.JANUARY, 9, 10, 11, 12); //Year, month, day of month, hours, minutes and seconds
        Date specificDateTime = cal.getTime();
        weatherReport.setDateToday(specificDateTime);
        weatherReport.setTimeAtSunrise(specificDateTime);
        cal.set(2013, Calendar.JANUARY, 9, 23, 15, 12); //Year, month, day of month, hours, minutes and seconds
        specificDateTime = cal.getTime();
        weatherReport.setTimeAtSunset(specificDateTime);
        weatherReport.setTemperatureInFahrenheit(72.0);
    }

    @Test
    public void serializeJson() throws IOException {
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathNumberValue("@.id")
                .isEqualTo(1234);
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.cityName")
                .isEqualTo("London");
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.cityName")
                .isNotEqualTo("Paris");
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.weatherDescription")
                .isEqualTo("Sunny with some clouds");
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathNumberValue("@.temperatureInFahrenheit")
                .isEqualTo(72.0);
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathNumberValue("@.temperatureInCelsius")
                .isEqualTo(22.22222222222222);
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.dateToday")
                .isEqualTo("09-01-2013");
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.timeAtSunrise")
                .isEqualTo("10:11am");
        assertThat(this.json.write(weatherReport))
                .extractingJsonPathStringValue("@.timeAtSunset")
                .isEqualTo("11:15pm");
    }

}
