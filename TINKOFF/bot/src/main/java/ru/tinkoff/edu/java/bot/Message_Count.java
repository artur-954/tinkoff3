package ru.tinkoff.edu.java.bot;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.telegram.telegrambots.meta.api.objects.Message;
public class Message_Count {
    private final Counter Message_Count;
    public Message_Count(MeterRegistry registry) {
        Message_Count = Counter.builder("сообщения")
                .description("количество сообщений")
                .register(registry);}
    public void processMessage(Message message) {
        Message_Count.increment();
    }
}