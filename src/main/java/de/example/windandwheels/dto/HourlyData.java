package de.example.windandwheels.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public record HourlyData( List<String> time,
        @JsonProperty("temperature_2m")
 List<Double> temperature2m,
@JsonProperty("wind_speed_10m")
 List<Double> windSpeed10m,
@JsonProperty("wind_gusts_10m")
 List<Double> windGusts10m,
@JsonProperty("precipitation_probability")
 List<Integer> precipitationProbability) {

}
