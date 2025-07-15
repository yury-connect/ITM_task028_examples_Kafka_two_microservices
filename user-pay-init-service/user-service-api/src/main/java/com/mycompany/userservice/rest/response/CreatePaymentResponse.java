package com.mycompany.userservice.rest.response;

import com.mycompany.userservice.rest.enums.StatusPayment;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreatePaymentResponse {

    private UUID id; // id, который мы получили при сохранении в БД

    private StatusPayment status;
}
