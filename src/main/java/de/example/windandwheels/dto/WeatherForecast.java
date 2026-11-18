package de.example.windandwheels.dto;

public record WeatherForecast(String time,
        double temperature,
        double windSpeed,
        double windGusts,
        int precipitationProbability) {
}
