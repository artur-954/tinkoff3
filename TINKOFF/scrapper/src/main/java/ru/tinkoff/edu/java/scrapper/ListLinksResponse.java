package ru.tinkoff.edu.java.scrapper;
import java.util.List;
public record ListLinksResponse(
        List<LinkResponse> links,
        int size) { }