package de.example.windandwheels.repository;

import de.example.windandwheels.entity.LocationEntity;
import de.example.windandwheels.entity.WeatherForecastEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class WeatherForecastRepositoryTest {
    private LocationRepository locationRepository;
    private WeatherForecastEntity weatherForecast;
    private WeatherForecastRepository weatherForecastRepository;

    @Autowired
    WeatherForecastRepositoryTest(
            LocationRepository locationRepository,
            WeatherForecastRepository weatherForecastRepository) {

        this.locationRepository = locationRepository;
        this.weatherForecastRepository = weatherForecastRepository;
    }

    @Test
    void shouldSaveAndFindWeatherForecast() {

        // 1. Ort erstellen und speichern
        LocationEntity kiel =
                new LocationEntity("Kiel", 54.32, 10.14);

        locationRepository.save(kiel);

        // 2. Wettervorhersage erstellen
        WeatherForecastEntity forecast =
                new WeatherForecastEntity(
                        kiel,
                        LocalDateTime.of(2012,12,12,12,12),
                        40,
                        41,
                        42,
                        43,
                        Instant.now()
                );

        // 3. Wettervorhersage speichern
        weatherForecastRepository.save(forecast);

        // 4. Gespeicherte Daten wieder auslesen
        List<WeatherForecastEntity> result =
                weatherForecastRepository
                        .findByLocationIdOrderByForecastTimeAsc(kiel);

        // 5. Prüfen
        assertEquals(1, result.size());

        // TODO: Temperatur überprüfen
        assertEquals(40,result.getFirst().getTemperature());
        // TODO: Windgeschwindigkeit überprüfen
        assertEquals(41,result.getFirst().getWindSpeed());
        // TODO: Vorhersagezeitpunkt überprüfen
        assertEquals(LocalDateTime.of(2012,12,12,12,12),result.getFirst().getForecastTime());
    }
}
