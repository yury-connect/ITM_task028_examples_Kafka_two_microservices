package com.mycompany.userservice.src.service;

import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusResponse;
import com.mycompany.userservice.rest.response.GetPaymentResponse;

import java.util.UUID;


/**
 * Интерфейс UserService.
 */
public interface UserService {

    CreatePaymentResponse sendPayment(CreatePaymentRequest request);

    GetPaymentStatusResponse getStatusSendingPayment(UUID id);

    GetPaymentResponse getPaymentById(UUID id);
}