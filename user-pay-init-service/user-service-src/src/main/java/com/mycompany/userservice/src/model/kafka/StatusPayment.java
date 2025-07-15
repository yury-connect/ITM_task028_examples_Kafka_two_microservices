package com.mycompany.userservice.src.model.kafka;

import com.mycompany.userservice.rest.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


/**
 * Сущность для передачи статуса платежа через Kafka
 */
@Getter
@Setter
@ToString
public class StatusPayment {

    private UUID paymentId; // id of payment

    private PaymentStatus paymentStatus; // status of payment
}
