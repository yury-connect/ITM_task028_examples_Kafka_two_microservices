package com.mycompany.userservice.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Главное приложение UserService.
 */
@SpringBootApplication
@EntityScan(basePackages = "com.mycompany.userservice.rest.model") // — указывает, где находятся JPA-сущности
@EnableJpaRepositories(basePackages = "com.mycompany.userservice.src.repository") // — указывает, где репозитории
@ComponentScan(basePackages = {
        "com.mycompany.userservice" // или укажи конкретно `controller`, `service`, и т.п.
}) // — подтягивает сервисы, контроллеры и прочее
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
