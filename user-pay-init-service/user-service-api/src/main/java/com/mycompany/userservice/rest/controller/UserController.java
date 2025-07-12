package com.mycompany.userservice.rest.controller;

import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/api")
public interface UserController {

    @GetMapping
    ResponseEntity<GetPaymentStatusUserResponse> getStatusSendingPayment(@RequestParam(name = "payment_id") @Valid UUID id);

    @PostMapping
    ResponseEntity<CreatePaymentUserResponse> sendPayment(@RequestBody @Valid CreatePaymentUserRequest request);
}
