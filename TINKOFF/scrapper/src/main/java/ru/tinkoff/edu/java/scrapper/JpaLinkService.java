package ru.tinkoff.edu.java.scrapper;

import com.github.dockerjava.api.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JpaLinkService implements LinkService {

    @Autowired
    private JpaLinkRepository linkRepository;

    @Override
    public void createLink(LinkDto linkDto) {
        Link link = new Link();
        link.setUrl(linkDto.getUrl());
        link.setDescription(linkDto.getDescription());
        linkRepository.save(link);
    }

    @Override
    public void deleteLink(Long id) {
        linkRepository.deleteById(id);
    }

    @Override
    public List<LinkDto> getAllLinks() {
        List<Link> links = linkRepository.findAll();
        return links.stream().map(link -> new LinkDto(link.getId(), link.getUrl(), link.getDescription()))
                .collect(Collectors.toList());
    }
}