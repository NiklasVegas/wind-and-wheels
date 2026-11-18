package de.example.windandwheels.dto;

import java.util.List;

public record WeatherResponse(String ort, List<WeatherForecast> forecasts) {

}
