package de.example.windandwheels.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "weather_forecasts")
public class WeatherForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private LocationEntity location_id;
    private LocalDateTime forecast_time;
    private double temperature;
    private double wind_speed;
    private double wind_gusts;
    private int precipitation_probability;
    private Instant fetchedAt;
}
