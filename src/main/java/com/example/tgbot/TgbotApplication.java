package com.example.tgbot;

import com.example.tgbot.bots.WeatherBot;
import com.example.tgbot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TgbotApplication {

    public static void main(String[] args)  {
        SpringApplication.run(TgbotApplication.class, args);
    }
    @Bean
    public TelegramBotsApi telegramBotsApi(WeatherBot weatherBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            botsApi.registerBot(weatherBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return botsApi;
    }

    @Bean
    public WeatherService weatherService(){
        return new WeatherService(webClient());
    }

    @Bean
    public WebClient webClient(){
        return WebClient.create("http://api.openweathermap.org/data/2.5/weather");//обрщаение к api погоды
    }

}
