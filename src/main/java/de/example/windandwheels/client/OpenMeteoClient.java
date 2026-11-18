package de.example.windandwheels.client;

import de.example.windandwheels.dto.OpenMeteoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;

@Component
public class OpenMeteoClient {
    static RestClient restClient = RestClient.create();
    String url = "https://api.opend-meteo.com/v1/forecast"
            + "?latitude=54.32"
            + "&longitude=10.14"
            + "&hourly=temperature_2m,precipitation_probability,"
            + "wind_speed_10m,wind_gusts_10m"
            + "&timezone=Europe%2FBerlin"
            + "&forecast_days=3";


    public OpenMeteoResponse fetchWeather(){
        return restClient.get()
                .uri(URI.create(url))
                .retrieve()
                .body(OpenMeteoResponse.class);
    }
}
