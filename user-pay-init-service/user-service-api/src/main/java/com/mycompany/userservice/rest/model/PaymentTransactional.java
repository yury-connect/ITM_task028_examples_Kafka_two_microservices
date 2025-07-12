package com.mycompany.userservice.rest.model;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentTransactional {

    @Id
    @NotNull
    @Column(name = "payment_id", unique = true)
    @GeneratedValue(generator = "uuid")
    private UUID id;

    // Одна транзакция - одна сумма. Каждый раз сумма платежа новая.
    @OneToOne
    @JoinColumn(name = "money_id")
    private Money money;

    // Много транзакций могут иметь одного и того-же пользователя
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
