package ru.tinkoff.edu.java.bot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/notification")
    public void handleHttpNotification(@RequestBody NotificationService notification) {
        notificationService.handleHttpNotification(notification);
    }
}