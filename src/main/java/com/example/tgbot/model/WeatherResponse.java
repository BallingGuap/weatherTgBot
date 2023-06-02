package com.example.tgbot.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherResponse {

    private String name;
    private Weather[] weather;
    private Main main;

    @Data
    public static class Weather{
        private String main;
        private String description;
    }
    @Getter
    @Setter
    public static class Main{
        private Double temp;
    }
}