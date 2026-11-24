package de.example.windandwheels.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_forecasts", uniqueConstraints = @UniqueConstraint(
        columnNames = {"location_id", "forecast_time"}
))
public class WeatherForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity locationId;
    @Column(name = "forecast_time")
    private LocalDateTime forecastTime;
    private double temperature;
    @Column(name = "wind_speed")
    private double windSpeed;
    @Column(name = "wind_gusts")
    private double windGusts;
    @Column(name = "precipitation_probability")
    private int precipitationProbability;
    private Instant fetchedAt;
}
