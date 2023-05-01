package ru.tinkoff.edu.java.bot.telegrambot;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static org.junit.jupiter.api.Assertions.*;

class MyTelegramBotTest {

    @Test
    void onUpdateReceived() {
        TelegramBotsApi telegramBotsApi = null;
        try {
            telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        try {
            telegramBotsApi.registerBot(new MyTelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}