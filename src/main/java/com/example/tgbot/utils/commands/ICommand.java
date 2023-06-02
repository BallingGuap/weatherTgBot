package com.example.tgbot.utils.commands;

import com.example.tgbot.bots.WeatherBot;
import com.example.tgbot.model.Weather;

public interface Command {
    void execute(Long chatId, WeatherBot bot);
}
