package ru.tinkoff.edu.java.bot;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue originalQueue() {
        return new Queue("o1");
    }
    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable("02.dlq")
                .withArgument("x-dead-letter-exchange", "o3.dlq.exchange")
                .build();
    }

    @Bean
    public Exchange originalExchange() {
        return new DirectExchange("o4.exchange");
    }

    @Bean
    public Exchange dlqExchange() {
        return new FanoutExchange("o3.dlq.exchange");
    }

    @Bean
    public Binding originalBinding() {
        return BindingBuilder.bind(originalQueue())
                .to(originalExchange())
                .with("routingKey")
                .noargs();
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue())
                .to(dlqExchange())
                .with("")
                .noargs();
    }
}