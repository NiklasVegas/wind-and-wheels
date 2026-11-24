package de.example.windandwheels.repository;

import de.example.windandwheels.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    boolean existsByName(String name);
    Optional<LocationEntity> findByName(String name);
}
