package ru.tinkoff.edu.java.scrapper;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void remove(User user);
    List<User> findAll();
}