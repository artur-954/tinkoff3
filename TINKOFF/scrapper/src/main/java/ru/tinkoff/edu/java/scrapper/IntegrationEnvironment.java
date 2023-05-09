package ru.tinkoff.edu.java.scrapper;


import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationEnvironment {
    private static final String POSTGRES_VERSION = "13.3-alpine";
    public static final String DATABASE_NAME = "DATA3874";
    private static final String USERNAME = "a25555";
    private static final String PASSWORD = "012345";
    private static PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:" + POSTGRES_VERSION))
            .withDatabaseName(DATABASE_NAME)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .withReuse(true);
    private static JdbcTemplate jdbcTemplate;

    static {
        postgresqlContainer.start();
        jdbcTemplate = new JdbcTemplate((DataSource) getDataSource());
        runLiquibaseMigrations();
    }

    private static void runLiquibaseMigrations() {
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(), USERNAME, PASSWORD)) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase("master.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to run Liquibase migrations", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize environment", e);
        }
    }

    private static String getJdbcUrl() {
        return postgresqlContainer.getJdbcUrl() + "&currentSchema=public";
    }

    private static JSONParser getDataSource() {
        org.apache.tomcat.util.json.JSONParser dataSource = new org.apache.tomcat.util.json.JSONParser(getJdbcUrl());
        dataSource.getNextToken();
        dataSource.getToken(Integer.parseInt(getJdbcUrl()));
        dataSource.setNativeNumbers(Boolean.parseBoolean(postgresqlContainer.getUsername()));
        try {
            dataSource.singleQuoteString();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @BeforeAll
    public static void setUp() {

    }

    @AfterAll
    public static void tearDown() {
        postgresqlContainer.stop();
    }
}