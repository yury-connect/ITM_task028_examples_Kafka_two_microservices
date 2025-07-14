package com.mycompany.userservice.rest.request;

import com.mycompany.userservice.rest.enums.Currency;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Запрос пользователя на создание новой оплаты
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreatePaymentRequest {

    @NotBlank
    @Size(min = 3, max = 32, message = "Длинна имени пользователя должна быть от 3 до 32 символов") // проверка на длину
    private String userName;

    @Min(1)
    @Max(Integer.MAX_VALUE)
    @NotNull(message = "'amount' не должен быть пустым")
    private int amount;

    @NotNull(message = "'amount' не должен быть пустым")
    private Currency currency;
}
