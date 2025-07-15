package com.mycompany.userservice.src.config.kafka;

import com.mycompany.userservice.src.config.KafkaProperties;
import com.mycompany.userservice.src.mapper.StatusPaymentMapper;
import com.mycompany.userservice.src.model.StatusPaymentEntity;
import com.mycompany.userservice.src.model.kafka.StatusPaymentDTO;
import com.mycompany.userservice.src.repository.StatusPaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Слушатель Kafka-топика статусов платежей.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StatusPaymentListener {

    private final StatusPaymentRepository repository;
    private final KafkaProperties kafkaProperties;
    private final StatusPaymentMapper mapper;

    @KafkaListener(topics = "#{@kafkaProperties.userStatusPaymentsTopic}", groupId = "user-service")
    public void listenStatus(StatusPaymentDTO dto) {
        log.info("StatusPaymentListener.listenStatus: Получено 'StatusPaymentDTO' о статусе платежа: {}", dto);


        if (dto.getStatusPayment() == null) {
            log.warn("StatusPaymentListener.listenStatus: ⚠️ StatusPaymentDTO содержит null в statusPayment — пропуск ⚠️");
            return;
        }

        StatusPaymentEntity entity = repository.save(mapper.toEntity(dto));
        log.info("StatusPaymentListener.listenStatus: Статус платежа сохранён в БД: {}", entity);
    }


    // Конфигурация Kafka Consumer (если потребуется вручную)
    // Если вдруг сообщения не десериализуются, добавь @Bean-конфигурацию:
//    @Bean
//    public ConsumerFactory<String, StatusPaymentDTO> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-service");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(props,
//                new StringDeserializer(),
//                new JsonDeserializer<>(StatusPaymentDTO.class, false));
//    }
}
