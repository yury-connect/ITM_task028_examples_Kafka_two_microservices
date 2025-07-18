package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Этот объект мы отдаем пользователю, когда он отправил запрос на оплату
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GetPaymentResponse {

    private String userName;

    private int coin;

    private Currency currency;
}
