package ru.tinkoff.edu.java.scrapper;

public interface TgChatService {
    void register(long tgChatId);
    void unregister(long tgChatId);
}