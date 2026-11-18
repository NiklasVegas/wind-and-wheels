package de.example.windandwheels.dto;

public record OpenMeteoResponse(double latitude,
        double longitude,
        HourlyData hourly) {

}
