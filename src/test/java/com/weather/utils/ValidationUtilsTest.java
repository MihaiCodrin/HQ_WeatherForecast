package com.weather.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ValidationUtilsTest {

    private ValidationUtils validationUtils;

    @Before
    public void setUp() {
        validationUtils = new ValidationUtils();
    }

    @Test
    public void whenValidCityCodeIsProvided_thenReturnedCityCodeIsNotNull()  {
        assertThat(validationUtils.isValidCityCode("1234")).isTrue();
    }

    @Test
    public void whenInvalidCityCodeIsProvided_thenInvalidCityCodeExceptionIsThrown()  {
        assertThat(validationUtils.isValidCityCode("123X")).isFalse();
    }

}
