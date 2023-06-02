package com.example.tgbot.utils.commands;

import com.example.tgbot.bots.WeatherBot;

public interface IWaitable {
    public boolean isWaitable();
    public void waitAnswer();
    public void answerExecute(Long chatId, String message, WeatherBot bot);
}