package com.example.tgbot.utils.commands;

import com.example.tgbot.bots.WeatherBot;

public interface ICommand {
    void execute(Long chatId, WeatherBot bot);
}
