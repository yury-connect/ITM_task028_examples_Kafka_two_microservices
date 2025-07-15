package com.mycompany.userservice.src.model.kafka;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mycompany.userservice.rest.enums.StatusPayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


/**
 * Сущность для передачи статуса платежа через Kafka.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StatusPaymentDTO {

    private UUID paymentId; // id платежа - из сущности 'Payment'

    @JsonSerialize
    @JsonDeserialize
    private StatusPayment statusPayment; // status платежа - из сущности 'StatusPayment'
}
