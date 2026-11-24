package de.example.windandwheels.config;

import de.example.windandwheels.entity.LocationEntity;
import de.example.windandwheels.repository.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LocationRepository locationRepository;

    public DataInitializer(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public void run(String... args) {

        // TODO: Prüfen, ob Kiel existiert
       boolean kielExists =  locationRepository.existsByName("Kiel");

        // TODO: Falls nicht, Kiel erstellen
        if(!kielExists) {
            LocationEntity kiel = new LocationEntity("Kiel", 54.32, 10.14);
            locationRepository.save(kiel);
        }
        // TODO: Kiel in der Datenbank speichern
    }
}