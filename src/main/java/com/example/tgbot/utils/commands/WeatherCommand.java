package com.example.tgbot.utils.commands;

import com.example.tgbot.bots.WeatherBot;
import com.example.tgbot.service.WeatherService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeatherCommand implements ICommand, IWaitable{
    boolean waitable = false;
    private final WeatherService weatherService;

    @Override
    public void execute(Long chatId, WeatherBot bot) {
        bot.sendText(chatId, "Type city");
    }
    @Override
    public void answerExecute(Long chatId,String message, WeatherBot bot) {
        weatherService.sendWeather(chatId, message, bot);
        waitable = false;
    }

    @Override
    public boolean isWaitable() {
        return waitable;
    }

    @Override
    public void waitAnswer() {
        waitable = true;
    }

}
