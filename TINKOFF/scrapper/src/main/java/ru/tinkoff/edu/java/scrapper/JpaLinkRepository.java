package ru.tinkoff.edu.java.scrapper;

import com.github.dockerjava.api.model.Link;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaLinkRepository extends Repository {
    void save(Link link);

    void deleteById(Long id);

    List<Link> findAll();
}