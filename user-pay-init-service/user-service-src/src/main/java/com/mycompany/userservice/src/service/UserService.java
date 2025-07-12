package com.mycompany.userservice.src.service;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentTransactionalUserResponse;

import java.util.UUID;


/**
 * Интерфейс UserService.
 */
public interface UserService {

    CreatePaymentUserResponse sendPayment(User user, Money payment);

    GetPaymentStatusUserResponse getStatusSendingPayment(UUID id);

    GetPaymentTransactionalUserResponse getPaymentById(UUID id);
}