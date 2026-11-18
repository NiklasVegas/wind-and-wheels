package de.example.windandwheels.client;

import de.example.windandwheels.dto.OpenMeteoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.net.URI;


@Component
public class OpenMeteoClient {

    private final RestClient restClient;

    public OpenMeteoClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://api.open-meteo.com")
                .build();
    }

    public OpenMeteoResponse fetchWeather() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", 54.32)
                        .queryParam("longitude", 10.14)
                        .queryParam("hourly",
                                "temperature_2m,precipitation_probability,"
                                        + "wind_speed_10m,wind_gusts_10m")
                        .queryParam("timezone", "Europe/Berlin")
                        .queryParam("forecast_days", 3)
                        .build())
                .retrieve()
                .body(OpenMeteoResponse.class);
    }
}

