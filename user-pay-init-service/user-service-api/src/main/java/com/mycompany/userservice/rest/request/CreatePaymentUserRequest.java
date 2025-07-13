package com.mycompany.userservice.rest.request;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.User;
import jakarta.validation.constraints.NotNull;
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
public class CreatePaymentUserRequest {

    @NotNull(message = "'user' не должен быть пустым")
    private User user;
    
    @NotNull(message = "'money' не должен быть пустым")
    private Money money;
}
