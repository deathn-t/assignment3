package com.example.Assignment3;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() throws URISyntaxException {
        String databaseUrl = System.getenv("DATABASE_URL");


        if (databaseUrl != null) {
            URI dbUri = new URI(databaseUrl);

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

            System.out.println("Using Render PostgreSQL database: " + dbUri.getHost());

            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .build();
        }


        String dbHost = System.getenv("DB_HOST");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbHost != null && dbUser != null && dbPassword != null) {
            String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s",
                    dbHost, dbPort != null ? dbPort : "5432",
                    dbName != null ? dbName : "ecommerce");

            System.out.println("Using Render PostgreSQL database with individual env vars: " + dbHost);

            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(dbUser)
                    .password(dbPassword)
                    .build();
        }

        // Final fallback for local development
        System.out.println("Using local PostgreSQL database");
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/ecommerce")
                .username("postgres")
                .password("password")
                .build();
    }
}