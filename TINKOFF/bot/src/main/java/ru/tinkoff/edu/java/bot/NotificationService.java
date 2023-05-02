package ru.tinkoff.edu.java.bot;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.UserRepository;

@Service
public class NotificationService {

    private final UserRepository userRepository;
    private final BotApplication botService;

    public NotificationService(UserRepository userRepository, BotApplication botService) {
        this.userRepository = userRepository;
        this.botService = botService;
    }

    public void handleLinkUpdate(LinkUpdateRequest update) {
        // обработка уведомления
    }

    public void handleHttpNotification(NotificationService notification) {
        // обработка уведомления
    }
}