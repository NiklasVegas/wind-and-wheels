package de.example.windandwheels.service;

import de.example.windandwheels.dto.WeatherForecast;
import de.example.windandwheels.dto.WeatherResponse;
import de.example.windandwheels.entity.LocationEntity;
import de.example.windandwheels.entity.WeatherForecastEntity;
import de.example.windandwheels.repository.LocationRepository;
import de.example.windandwheels.repository.WeatherForecastRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public WeatherResponse getStoredKielForecast(){
        LocationEntity kiel = locationRepository.findByName("Kiel")
                .orElseThrow(() -> new IllegalStateException(
                        "Kiel wurde nicht gefunden"
                ));
        List<WeatherForecastEntity> entities =
                weatherForecastRepository
                        .findByLocationIdOrderByForecastTimeAsc(kiel);
        List<WeatherForecast> forecasts = createForecasts(entities);
        return new WeatherResponse("Kiel", forecasts);
    }

    private static @NonNull List<WeatherForecast> createForecasts(List<WeatherForecastEntity> entities) {
        List<WeatherForecast> forecasts = new ArrayList<>();

        for (WeatherForecastEntity entity : entities) {

            WeatherForecast forecast = new WeatherForecast(entity.getForecastTime().toString(),entity.getTemperature(),entity.getWindSpeed(), entity.getWindGusts(), entity.getPrecipitationProbability());
            forecasts.add(forecast);
        }
        return forecasts;
    }

    public void refreshKielForecast(){

        //Prüfen, ob Kiel existiert
        LocationEntity kiel = locationRepository.findByName("Kiel")
                .orElseThrow(() -> new IllegalStateException(
                        "Kiel wurde nicht in der Datenbank gefunden"
                ));

        //aktuelle Forecasts für Kiel von OpenMeteo abrufen
        List<WeatherForecast> forecasts = weatherService.getForecast();

        //über forecasts iterieren
        for (WeatherForecast forecast : forecasts) {

            //Zeitpunkt in LocalDateTime umwandeln
            LocalDateTime forecastTime = LocalDateTime.parse(forecast.time());

            double temperature = forecast.temperature();
            double windSpeed = forecast.windSpeed();
            double windGusts = forecast.windGusts();
            int precipitationProbability = forecast.precipitationProbability();

            //Prüfen, ob es für diesen Zeitpunkt bereits entity gibt, sonst erstellen
            Optional<WeatherForecastEntity> existing =
                    weatherForecastRepository.findByLocationIdAndForecastTime(
                            kiel, forecastTime
                    );
            WeatherForecastEntity entity;
            if(existing.isPresent()){
                entity = existing.get();
                entity.updateWeather(temperature,windSpeed,windGusts,precipitationProbability);

            } else {
                entity = new WeatherForecastEntity(kiel,forecastTime,temperature,windSpeed, windGusts, precipitationProbability, Instant.now());
            }
            weatherForecastRepository.save(entity);

        }
    }
}
