package ru.tinkoff.edu.java.scrapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionTemplate transactionTemplate;

    public UserService(UserRepository userRepository, TransactionTemplate transactionTemplate) {
        this.userRepository = userRepository;
        this.transactionTemplate = transactionTemplate;
    }

    public void addUsers(List<User> users) {
        transactionTemplate.execute(status -> {
            for (User user : users) {
                userRepository.add(user);
            }
            return null;
        });
    }

    public void removeUser(User user) {
        transactionTemplate.execute(status -> {
            userRepository.remove(user);
            return null;
        });
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}