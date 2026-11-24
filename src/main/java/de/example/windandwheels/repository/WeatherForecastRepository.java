package de.example.windandwheels.repository;

import de.example.windandwheels.entity.LocationEntity;
import de.example.windandwheels.entity.WeatherForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherForecastRepository extends JpaRepository<WeatherForecastEntity, Long> {
    List<WeatherForecastEntity> findByLocationIdOrderByForecastTimeAsc(LocationEntity location);
    Optional<WeatherForecastEntity> findByLocationIdAndForecastTime(LocationEntity locationId, LocalDateTime time);
}
