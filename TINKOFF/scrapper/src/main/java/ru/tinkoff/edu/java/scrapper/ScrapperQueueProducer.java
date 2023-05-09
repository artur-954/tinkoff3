package ru.tinkoff.edu.java.scrapper;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Service
@RequiredArgsConstructor
public class ScrapperQueueProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationConfig applicationConfig;

    public void send(LinkUpdater update) {
        rabbitTemplate.convertAndSend(
                applicationConfig.getExchangeName(),
                applicationConfig.getRoutingKey(),
                update
        );
    }
}