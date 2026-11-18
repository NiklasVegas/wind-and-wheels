package de.example.windandwheels.service;

import de.example.windandwheels.client.OpenMeteoClient;
import de.example.windandwheels.dto.HourlyData;
import de.example.windandwheels.dto.OpenMeteoResponse;
import de.example.windandwheels.dto.WeatherForecast;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WeatherServiceTest {

    @Test
    void shouldConvertHourlyWeatherData() {

        // Hier kommt dein Test hin
        HourlyData hourly = new HourlyData(
                List.of("2026-11-21T10:00", "2026-11-21T11:00"),
                List.of(15.0, 17.0),
                List.of(12.0, 14.0),
                List.of(19.0, 22.0),
                List.of(20, 35)
        );
        OpenMeteoResponse response = new OpenMeteoResponse(54.32006,10.137984,hourly);
        OpenMeteoClient mockClient =
                Mockito.mock(OpenMeteoClient.class);
        Mockito.when(mockClient.fetchWeather())
                .thenReturn(response);
        WeatherService service = new WeatherService(mockClient);
        List<WeatherForecast> result = service.getForecast();
        assertEquals(2,result.size());
        assertEquals(15, result.getFirst().temperature());
        assertEquals(12, result.getFirst().windSpeed());
        assertEquals(35, result.get(1).precipitationProbability());
        assertTrue(result.get(1).time().endsWith("T11:00"));
        Mockito.verify(mockClient).fetchWeather();


    }
}