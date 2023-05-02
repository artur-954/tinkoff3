package ru.tinkoff.edu.java.scrapper;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationEnvironment {
    private static final String POSTGRES_VERSION = "13.3-alpine";
    private static final String DATABASE_NAME = "test";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";
    private static PostgreSQLContainer<?> postgresqlContainer;
    private static JdbcTemplate jdbcTemplate;

    static {
        postgresqlContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:" + POSTGRES_VERSION))
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD)
                .withReuse(true);
        postgresqlContainer.start();
        jdbcTemplate = new JdbcTemplate(getDataSource());
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

    private static org.apache.tomcat.jdbc.pool.DataSource getDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(postgresqlContainer.getDriverClassName());
        dataSource.setUrl(getJdbcUrl());
        dataSource.setUsername(postgresqlContainer.getUsername());
        dataSource.setPassword(postgresqlContainer.getPassword());
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