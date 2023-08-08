package com.weather.controller;

import com.weather.beans.WeatherReport;
import com.weather.service.IWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IWeatherService weatherServiceMock;

    private String cityCode = "1234";
    private WeatherReport weatherReport;

    @Before
    public void setup() {
        weatherReport = new WeatherReport();
        weatherReport.setId(new Long(1234));
        weatherReport.setCityName("London");
        weatherReport.setTemperatureInFahrenheit(72.00);
        weatherReport.setTimeAtSunrise(new Date());
        weatherReport.setTimeAtSunset(new Date());
    }

    @Test
    public void whenCityCodeIsProvided_thenResponseStatusIs200() throws Exception {
        given(weatherServiceMock.getCurrentWeatherForCity(cityCode)).willReturn(Optional.of(weatherReport));
        mockMvc.perform(get("/weather/current/"+cityCode))
                .andExpect(status().isOk());
    }

    @Test
    public void whenNonExistingCityCodeIsProvided_thenResponseStatusIs5xxServerError() throws Exception {
        given(weatherServiceMock.getCurrentWeatherForCity(cityCode)).willReturn(Optional.empty());
        mockMvc.perform(get("/weather/current/"+cityCode).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }


}
