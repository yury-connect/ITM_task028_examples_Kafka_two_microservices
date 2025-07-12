package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.enums.PaymentTransactionalStatus;
import lombok.Builder;
import lombok.ToString;


/**
 * Этот объект мы отдаем пользователю, когда он отправил запрос на получения статуса оплаты по ID
 */
@ToString
@Builder
public class GetPaymentStatusUserResponse {

    private PaymentTransactionalStatus status;

}
