package com.weather.service;

import com.weather.beans.CurrentWeather2WeatherReportMapper;
import com.weather.beans.WeatherReport;
import com.weather.exception.InvalidCityCodeException;
import com.weather.utils.ValidationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:application.properties")
public class WeatherServiceTest {

    private IWeatherService weatherService;

    @Value("${open.weather.map.api.key}")
    private String openWeatherMapApiKey;

    @MockBean
    private CurrentWeather2WeatherReportMapper mapperMock;

    @MockBean
    private ValidationUtils validationUtilsMock;

    private WeatherReport expectedWeatherReport;

    @Before
    public void setUp() {
        weatherService = new WeatherService(openWeatherMapApiKey, mapperMock, validationUtilsMock);
        expectedWeatherReport = new WeatherReport();
        expectedWeatherReport.setId(new Long(1235));
        expectedWeatherReport.setCityName("London");
        expectedWeatherReport.setTemperatureInFahrenheit(72.00);
        expectedWeatherReport.setTimeAtSunrise(new Date());
        expectedWeatherReport.setTimeAtSunset(new Date());
    }

    @Test
    public void whenCityCodeIsProvided_thenRetrievedCityNameIsCorrect() {
        given(mapperMock.map(any())).willReturn(Optional.of(expectedWeatherReport));
        given(validationUtilsMock.isValidWeatherResponse(any())).willReturn(true);
        given(validationUtilsMock.isValidCityCode(any())).willReturn(true);
        Optional<WeatherReport> actualWeatherReport = weatherService.getCurrentWeatherForCity("1234");
        assertThat(actualWeatherReport.get().getCityName()).isEqualTo("London");
    }

    @Test(expected = InvalidCityCodeException.class)
    public void whenInvalidCityCodeIsProvided_thenInvalidCityCodeExceptionIsThrown() {
        given(validationUtilsMock.isValidCityCode(any())).willReturn(false);
        weatherService.getCurrentWeatherForCity("123X");
    }
}