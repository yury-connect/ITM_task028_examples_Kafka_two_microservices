package com.mycompany.userservice.rest.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


/**
 * Запрос пользователя о состоянии оплаты
 * id - идентификатор оплаты, выданный пользователю после отправки платежа
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GetStatusPaymentRequest {

    @NotNull(message = "поле 'uuid' не должно быть пустым")
    private UUID id;
}
