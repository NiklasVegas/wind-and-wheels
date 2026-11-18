package de.example.windandwheels.service;

import de.example.windandwheels.client.OpenMeteoClient;
import de.example.windandwheels.dto.HourlyData;
import de.example.windandwheels.dto.OpenMeteoResponse;
import de.example.windandwheels.dto.WeatherForecast;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final OpenMeteoClient openMeteoClient;

    public WeatherService(OpenMeteoClient openMeteoClient) {
        this.openMeteoClient = openMeteoClient;
    }

    public List<WeatherForecast> getForecast() {

        OpenMeteoResponse response = openMeteoClient.fetchWeather();

        HourlyData hourly = response.hourly();

        List<WeatherForecast> forecasts = new ArrayList<>();

        for (int i = 0; i < hourly.time().size(); i++) {

            // Daten für Index i auslesen
            String time = hourly.time().get(i);
            double temperature = hourly.temperature2m().get(i);
            double windSpeed10m = hourly.windSpeed10m().get(i);
            double windGusts = hourly.windGusts10m().get(i);
            int precipitationProbability = hourly.precipitationProbability().get(i);
            // WeatherForecast erstellen
            WeatherForecast forecast = new WeatherForecast(time,temperature,windSpeed10m,windGusts,precipitationProbability);
            // Objekt zur Liste hinzufügen
            forecasts.add(forecast);
        }
        return forecasts;
    }
}
