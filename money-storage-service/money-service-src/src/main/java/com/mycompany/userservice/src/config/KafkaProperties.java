package com.mycompany.userservice.src.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Конфигурация Kafka-топиков.
 */
@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "app.kafka")
public class KafkaProperties {
    private String userPaymentsTopic;         // топик отправки платежей
    private String userStatusPaymentsTopic;   // топик получения статусов
}
