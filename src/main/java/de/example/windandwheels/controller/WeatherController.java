package de.example.windandwheels.controller;

import de.example.windandwheels.dto.WeatherForecast;
import de.example.windandwheels.dto.WeatherResponse;
import de.example.windandwheels.repository.LocationRepository;
import de.example.windandwheels.repository.WeatherForecastRepository;
import de.example.windandwheels.service.WeatherPersistenceService;
import de.example.windandwheels.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherPersistenceService weatherPersistenceService;


    public WeatherController(WeatherService weatherService, WeatherPersistenceService weatherPersistenceService) {
        this.weatherService = weatherService;
        this.weatherPersistenceService = weatherPersistenceService;

    }

    @GetMapping("/api/weather")
    public List<WeatherForecast> getWeather() {
        return weatherService.getForecast();
    }

    @PostMapping("/api/weather/refresh")
    public void refreshWeather() {weatherPersistenceService.refreshKielForecast();}

    @GetMapping("/api/weather/stored")
    public WeatherResponse getStoredWeather() {return weatherPersistenceService.getStoredKielForecast();}
}
