package com.mycompany.userservice.src.service;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;

import java.util.UUID;


/**
 * Интерфейс UserService.
 */
public interface UserService {

    CreatePaymentUserResponse sendPayment(User user, Money payment);

    User findById(Long id);

    GetPaymentStatusUserResponse getStatusSendingPayment(UUID id);

}