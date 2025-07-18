package com.mycompany.userservice.src.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
 * Сущность для хранения оплаты в БД
 */
@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {

    @Id
    @Column(name = "payment_id", unique = true)
    @NotNull
    @GeneratedValue(generator = "uuid")
    private UUID id;

    // Одна транзакция - одна сумма. Каждый раз сумма платежа новая.
    @OneToOne
    @JoinColumn(name = "money_id")
    @NotNull
    private Money money;

    // Много транзакций могут иметь одного и того-же пользователя
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @CreationTimestamp
    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;
}
