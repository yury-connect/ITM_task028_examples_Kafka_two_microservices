package com.mycompany.userservice.src.service;

import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import com.mycompany.userservice.rest.response.GetPaymentResponse;

import java.util.UUID;


public interface UserService {

    CreatePaymentResponse sendPayment(CreatePaymentRequest request);

    GetStatusPaymentResponse getStatusPayment(UUID id);

    GetPaymentResponse getPayment(UUID id);
}