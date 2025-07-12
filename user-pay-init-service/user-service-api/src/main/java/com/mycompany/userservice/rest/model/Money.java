package com.mycompany.userservice.rest.model;

import com.mycompany.userservice.rest.enums.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


@Entity
@Table(name = "moneys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Money {

    @Id
    @Column(name = "money_id", unique = true)
    @NotNull   // для любых объектов (не null).
    @GeneratedValue(generator = "uuid")
    private UUID id;

    // Сумма
    @Column(name = "money_coin")
    @NotNull   // для любых объектов (не null).
    @Size(min = 1, max = Integer.MAX_VALUE, message = "Сумма платежа должна быть больше 0")
    private int coin;

    // Валюта
    @Column(name = "money_currency")
    @NotNull   // для любых объектов (не null).
//    @Size(min = 3, max = 3, message = "В именовании валюты по стандарту ISO 4217 используется трехбуквенный алфавитный код.") // проверка на длину
    private Currency currency;
}


