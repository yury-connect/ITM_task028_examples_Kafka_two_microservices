package com.mycompany.userservice.rest.controller;

import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@RequestMapping("/api")
public interface UserController {

    @PostMapping
    ResponseEntity<CreatePaymentUserResponse> sendPayment(@RequestBody @Valid CreatePaymentUserRequest request);

    @GetMapping
    ResponseEntity<GetPaymentStatusUserResponse> getStatusSendingPayment(@RequestBody @Valid UUID id);
}
