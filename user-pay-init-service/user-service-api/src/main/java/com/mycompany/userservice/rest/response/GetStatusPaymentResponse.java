package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.enums.StatusPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Этот объект мы отдаем пользователю, когда он отправил запрос на получения статуса оплаты по ID
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GetStatusPaymentResponse {

    private StatusPayment status;
}
