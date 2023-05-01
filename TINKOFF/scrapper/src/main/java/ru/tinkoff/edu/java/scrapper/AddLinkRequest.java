package ru.tinkoff.edu.java.scrapper;
import jakarta.validation.constraints.NotBlank;
public record AddLinkRequest(
        @NotBlank
        String link) { }