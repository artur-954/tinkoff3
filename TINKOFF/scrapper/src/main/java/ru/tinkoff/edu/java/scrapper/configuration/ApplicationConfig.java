package ru.tinkoff.edu.java.scrapper.configuration;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test) {
    public String getExchangeName() {
        return "ExchangeName";
    }

    public String getQueueName() {
        return "QueueName";
    }

    public String getRoutingKey() {
        return "RoutingKey";
    }
}
