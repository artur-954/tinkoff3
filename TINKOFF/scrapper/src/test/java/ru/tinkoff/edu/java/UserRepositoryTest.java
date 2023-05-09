package ru.tinkoff.edu.java;

import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.scrapper.UserRepository;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest {
    @Test
    public void testRemoveMethodExists() {
        List<Method> methods = Arrays.asList(UserRepository.class.getDeclaredMethods());
        boolean methodExists = methods.stream()
                .anyMatch(method -> method.getName().equals("remove"));
        assertTrue(methodExists);
    }
}