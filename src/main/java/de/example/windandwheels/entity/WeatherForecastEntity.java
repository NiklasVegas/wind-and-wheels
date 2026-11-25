package de.example.windandwheels.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "weather_forecasts", uniqueConstraints = @UniqueConstraint(
        columnNames = {"location_id", "forecast_time"}
))
public class WeatherForecastEntity {

    public WeatherForecastEntity() {

    }

    public Long getId() {
        return id;
    }

    public LocationEntity getLocationId() {
        return locationId;
    }

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindGusts() {
        return windGusts;
    }

    public int getPrecipitationProbability() {
        return precipitationProbability;
    }

    public Instant getFetchedAt() {
        return fetchedAt;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindGusts(double windGusts) {
        this.windGusts = windGusts;
    }

    public void setPrecipitationProbability(int precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    public void setFetchedAt(Instant fetchedAt) {
        this.fetchedAt = fetchedAt;
    }

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



    public WeatherForecastEntity(LocationEntity locationId, LocalDateTime forecastTime, double temperature, double windSpeed, double windGusts, int precipitationProbability, Instant fetchedAt) {
        this.locationId = locationId;
        this.forecastTime = forecastTime;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windGusts = windGusts;
        this.precipitationProbability = precipitationProbability;
        this.fetchedAt = fetchedAt;
    }

    public void updateWeather(
            double temperature,
            double windSpeed,
            double windGusts,
            int precipitationProbability) {

        if (!Objects.equals(this.temperature, temperature)
                || !Objects.equals(this.windSpeed, windSpeed)
                || !Objects.equals(this.windGusts, windGusts)
                || !Objects.equals(this.precipitationProbability, precipitationProbability)) {

            this.temperature = temperature;
            this.windSpeed = windSpeed;
            this.windGusts = windGusts;
            this.precipitationProbability = precipitationProbability;
            this.fetchedAt = Instant.now();
        }
    }
}
