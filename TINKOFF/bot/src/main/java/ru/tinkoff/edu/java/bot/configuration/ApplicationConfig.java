package ru.tinkoff.edu.java.bot.configuration;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import java.util.Optional;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(@NotNull String test) {
    private static final String TELEGRAM_BOT_TOKEN_KEY = "telegrambot_token";

    public static String getTelegramBotToken() {
        return Optional.ofNullable(System.getenv(TELEGRAM_BOT_TOKEN_KEY))
                .orElseThrow(() -> new IllegalStateException("Проблемы с токеном"));
    }

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

