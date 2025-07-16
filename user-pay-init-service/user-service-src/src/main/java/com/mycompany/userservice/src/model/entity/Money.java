package com.mycompany.userservice.src.model.entity;

import com.mycompany.userservice.rest.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Entity
@Table(name = "moneys")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Money {

    @Id
    @Column(name = "money_id", unique = true)
    @NotNull
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(name = "money_amount")
    @NotNull
    @Min(1)
    @Max(Integer.MAX_VALUE)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "money_currency")
    @NotNull
    private Currency currency;
}


