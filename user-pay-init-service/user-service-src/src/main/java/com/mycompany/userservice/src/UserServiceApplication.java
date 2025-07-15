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
@EntityScan(basePackages = {                        // — указывает, где находятся JPA-сущности
        "com.mycompany.userservice.src.model",      // 👉 для StatusPaymentEntity и других JPA-сущностей
        "com.mycompany.userservice.rest.model"      // 👉 если есть JPA-сущности и там
})
@EnableJpaRepositories(basePackages = "com.mycompany.userservice.src.repository") // — указывает, где репозитории
@ComponentScan(basePackages = {
        "com.mycompany.userservice" // подтягивает всё: controller, service, mapper и др.
}) // — подтягивает сервисы, контроллеры и прочее
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
