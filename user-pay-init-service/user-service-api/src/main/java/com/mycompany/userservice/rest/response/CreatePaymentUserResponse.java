package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.response.enums.PaymentTransactionalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;


/**
 * Этот объект мы отдаем пользователю, когда он отправил запрос на оплату
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreatePaymentUserResponse {

    private UUID paymentTransactionalId; // id, который мы получили при сохранении в БД

    private PaymentTransactionalStatus status;
}

