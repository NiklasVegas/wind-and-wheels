package de.example.windandwheels.service;

import de.example.windandwheels.dto.WeatherForecast;
import de.example.windandwheels.entity.LocationEntity;
import de.example.windandwheels.entity.WeatherForecastEntity;
import de.example.windandwheels.repository.LocationRepository;
import de.example.windandwheels.repository.WeatherForecastRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WeatherPersistenceService {
    private final LocationRepository locationRepository;
    private final WeatherService weatherService;
    private final WeatherForecastRepository weatherForecastRepository;

    public WeatherPersistenceService(LocationRepository locationRepository,
                                     WeatherService weatherService,
                                     WeatherForecastRepository weatherForecastRepository) {
        this.locationRepository = locationRepository;
        this.weatherService = weatherService;
        this.weatherForecastRepository = weatherForecastRepository;
    }

    public void refreshKielForecast(){
        LocationEntity kiel = locationRepository.findByName("Kiel")
                .orElseThrow(() -> new IllegalStateException(
                        "Kiel wurde nicht in der Datenbank gefunden"
                ));
        List<WeatherForecast> forecasts = weatherService.getForecast();
        for (WeatherForecast forecast : forecasts) {

            LocalDateTime forecastTime = LocalDateTime.parse(forecast.time());
            Optional<WeatherForecastEntity> existing =
                    weatherForecastRepository.findByLocationIdAndForecastTime(
                            kiel, forecastTime
                    );
            WeatherForecastEntity entity;
            if(existing.isPresent()){
                entity = existing.get();
            } else {

            }
        }
    }
}
