package de.example.windandwheels.controller;

import de.example.windandwheels.dto.WeatherForecast;
import de.example.windandwheels.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/api/weather")
    public List<WeatherForecast> getWeather() {
        return weatherService.getForecast();
    }
}
