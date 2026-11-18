package de.example.windandwheels.controller;

import de.example.windandwheels.client.OpenMeteoClient;
import de.example.windandwheels.dto.HelloResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public static HelloResponse hello(@RequestParam(value = "name", defaultValue = "Welt") String name) {
       // return new HelloResponse(String.format("Hallo, %s", name));
        OpenMeteoClient omc = new OpenMeteoClient();
        return new HelloResponse(omc.fetchWeather());
    }
}
