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

        // If DATABASE_URL is provided (Render production)
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
        // Fallback for local development
        else {
            System.out.println("Using local PostgreSQL database");
            return DataSourceBuilder.create()
                    .url("jdbc:postgresql://localhost:5432/ecommerce")
                    .username("postgres")
                    .password("password")
                    .build();
        }
    }
}