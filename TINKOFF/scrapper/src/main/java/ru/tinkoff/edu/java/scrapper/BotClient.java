package ru.tinkoff.edu.java.scrapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Component
public class BotClient {
    private final RestTemplate restTemplate;
    private final ApplicationConfig applicationConfig;

    public BotClient(RestTemplate restTemplate, ApplicationConfig applicationConfig) {
        this.restTemplate = restTemplate;
        this.applicationConfig = applicationConfig;
    }

    public void sendMessage(String message) {
        String url = applicationConfig.getBotUrl() + "/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = "{\"chat_id\":\"" + applicationConfig.getChatId() + "\",\"text\":\"" + message + "\"}";
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        restTemplate.postForObject(url, request, String.class);
    }
}