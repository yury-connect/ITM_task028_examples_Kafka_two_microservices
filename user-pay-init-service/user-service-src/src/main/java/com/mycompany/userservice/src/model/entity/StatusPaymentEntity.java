package com.mycompany.userservice.src.model.entity;

import com.mycompany.userservice.rest.enums.StatusPayment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Сущность для хранения статуса платежа в БД.
 */
@Entity
@Table(name = "status_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StatusPaymentEntity {

    @Id
    @Column(name = "id", unique = true)
    @NotNull
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotNull
    @Column(name = "payment_id", unique = true)
    private UUID paymentId; // id платежа - из сущности 'Payment'

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPayment statusPayment; // status платежа - из enum 'StatusPayment'

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
}
