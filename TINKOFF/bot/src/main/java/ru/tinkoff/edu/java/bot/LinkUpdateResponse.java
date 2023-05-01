package ru.tinkoff.edu.java.bot;
import org.springframework.http.HttpStatus;
public record LinkUpdateResponse(
        String message,
        HttpStatus statusCode) { }