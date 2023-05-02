package ru.tinkoff.edu.java.bot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.java.scrapper.NotificationService;

@RabbitListener(queues = "${app.scrapper-queue.name}")
public class ScrapperQueueListener {

    private NotificationService notificationService = null;

    public ScrapperQueueListener() {
        this.notificationService = notificationService;
    }

    @RabbitHandler
    public void receive(LinkUpdateRequest update) {
        System.out.println("receive");
    }
}
