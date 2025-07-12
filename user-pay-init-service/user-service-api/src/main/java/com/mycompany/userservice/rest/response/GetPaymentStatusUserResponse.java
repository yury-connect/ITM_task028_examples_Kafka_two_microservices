package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.response.enums.PaymentTransactionalStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.UUID;


/**
 * Этот объект мы отдаем пользователю, когда он отправил запрос на получения статуса оплаты по ID
 */
@Builder
public class GetPaymentStatusUserResponse {

    private PaymentTransactionalStatus status;

}
