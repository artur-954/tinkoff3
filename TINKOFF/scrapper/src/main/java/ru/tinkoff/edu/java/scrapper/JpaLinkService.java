package ru.tinkoff.edu.java.scrapper;

import com.github.dockerjava.api.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaLinkService implements LinkService {

    @Autowired
    private JpaLinkRepository linkRepository;

    @Override
    public Link save(Link link) {
        return null;
    }

    @Override
    public Link add(long tgChatId, URI url) {
        return null;
    }

    @Override
    public Link remove(long tgChatId, URI url) {
        return null;
    }

    @Override
    public Collection<Link> listAll(long tgChatId) {
        return null;
    }

    @Override
    public Optional<Link> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void createLink(LinkDto linkDto) {
        Link link = new Link("container1", "alias1");
        link.getName();
        link.equals(linkDto.getDescription());
        linkRepository.save(link);
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public List<LinkDto> getAllLinks() {
        List<Link> links = linkRepository.findAll();
        return (List<LinkDto>) links.stream().map(link -> new LinkDto(link.getName(), link.getAlias(), link.toString()));
    }
}