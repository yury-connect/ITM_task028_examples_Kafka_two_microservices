package com.mycompany.userservice.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;


/**
 * Запрос пользователя о состоянии оплаты
 * paymentTransactionalId - идентификатор оплаты, выданный пользователю после отправки платежа
 */
public class GetPaymentStatusUserRequest {

    @NotNull(message = "поле 'uuid' не должно быть пустым")
    private UUID paymentTransactionalId;

}
