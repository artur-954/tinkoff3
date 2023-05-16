package ru.tinkoff.edu.java.scrapper;

import com.github.dockerjava.api.model.Link;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LinkService {
    @Transactional
    Link save(Link link);

    Link add(long tgChatId, URI url);
    Link remove(long tgChatId, URI url);
    Collection<Link> listAll(long tgChatId);

    Optional<Link> findById(Long id);

    void createLink(LinkDto linkDto);

    void deleteLink(Long id);

    List<LinkDto> getAllLinks();
}