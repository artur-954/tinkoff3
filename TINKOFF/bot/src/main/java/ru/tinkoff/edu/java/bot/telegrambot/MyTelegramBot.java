package ru.tinkoff.edu.java.bot.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

import java.util.ArrayList;
import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {
    private final String token;

    public MyTelegramBot()  {
        this.token = ApplicationConfig.getTelegramBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();
            switch (text) {
                case "/start":
                    break;
                case "/help":
                    String helpText = "Список команд:\n" +
                            "/start - зарегистрировать пользователя\n" +
                            "/help -- вывести окно с командами\n" +
                            "/track -- начать отслеживание ссылки\n" +
                            "/untrack -- прекратить отслеживание ссылки\n" +
                            "/list -- показать список отслеживаемых ссылок\n";
                    SendMessage response = new SendMessage();
                            response.setChatId(message.getChatId());
                            response.setText(helpText);
                    try {
                        execute(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "/track":
                    break;
                case "/untrack":
                    break;
                case "/list":
                    break;
                default:
                    long chatId = message.getChatId();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Такой команды нет");

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "tin789_bot";
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public static void main(String[] args) {
        MyTelegramBot bot = new MyTelegramBot();

        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("start", "зарегистрировать пользователя"));
        commands.add(new BotCommand("help", "вывести окно с командами"));
        commands.add(new BotCommand("track", "начать отслеживание ссылки"));
        commands.add(new BotCommand("untrack", "прекратить отслеживание ссылки"));
        commands.add(new BotCommand("list", "показать список отслеживаемых ссылок"));

        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(commands);

        try {
            bot.execute(setMyCommands);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId("tin789_bot");
            sendMessage.setText("Бот готов");
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}