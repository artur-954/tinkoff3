package ru.tinkoff.edu.java.scrapper;

import io.swagger.v3.oas.models.links.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLinkRepository extends JpaRepository<Link, Long> {
}