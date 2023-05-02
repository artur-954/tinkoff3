package ru.tinkoff.edu.java.scrapper;

import com.github.dockerjava.api.model.Link;

import java.net.URI;
import java.util.Collection;

public interface LinkService {
    Link add(long tgChatId, URI url);
    Link remove(long tgChatId, URI url);
    Collection<Link> listAll(long tgChatId);
}