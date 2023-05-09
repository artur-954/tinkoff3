package ru.tinkoff.edu.java;

import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.scrapper.IntegrationEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkControllerIntegrationTest {

        @Test
        public void testDatabaseName() {
            assertEquals("DATA3874", IntegrationEnvironment.DATABASE_NAME);
        }
    }

