package com.example.tgbot.service;

import com.example.tgbot.bots.WeatherBot;
import com.example.tgbot.model.Weather;
import com.example.tgbot.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class WeatherService {
    final   String apiKey = "3ee1f26e5f4dea1fbfe1eb03204bffaf";
    private final WebClient webClient;
    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void sendWeather(Long chatId, String city, WeatherBot weatherBot){
        try {
            var weatherResponse = buildWeatherResponse(city).orElseThrow(() ->  new RuntimeException("Invalid request"));
            var weather = parseWeatherResponse(weatherResponse);
            weatherBot.sendText(chatId, getMessage(weather));
        }
        catch (RuntimeException e){
            weatherBot.sendText(chatId, e.getMessage());
        }

    }

    private String getMessage(Weather weather){
        return "In " + weather.getName() + ": " + weather.getTemp() + "K";
    }

   private Weather parseWeatherResponse(WeatherResponse res){
        return new Weather(res.getName(),
                res.getMain().getTemp());
   }
    private Optional<WeatherResponse> buildWeatherResponse(String cityName){
        try {
            return Optional.ofNullable(webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .queryParam("q", cityName)
                            .queryParam("appid", apiKey)
                            .build())
                    .retrieve()
                    .bodyToMono(WeatherResponse.class)
                    .block());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

}
