package ru.tinkoff.edu.java.scrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final ScrapperQueueProducer queueProducer;
    private final BotClient botClient;
    private final ApplicationConfig applicationConfig;

    public void sendNotification(String message) {
        if (applicationConfig.isUseQueue()) {
            queueProducer.send(() -> 0);
        } else {
            botClient.sendMessage(message);
        }
    }
}