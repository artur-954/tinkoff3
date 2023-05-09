package ru.tinkoff.edu.java.scrapper;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Configuration
public class RabbitMQConfiguration {
    @Autowired
    private ApplicationConfig applicationConfig;

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(applicationConfig.getExchangeName());
    }

    @Bean
    public Queue queue() {
        return new Queue(applicationConfig.getQueueName());
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(applicationConfig.getRoutingKey());
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}