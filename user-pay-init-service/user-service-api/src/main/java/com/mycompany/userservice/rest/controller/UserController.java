package com.mycompany.userservice.rest.controller;

import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import com.mycompany.userservice.rest.response.GetPaymentResponse;
import jakarta.validation.Valid;
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
    ResponseEntity<GetStatusPaymentResponse> getStatusPayment(@RequestParam(name = "payment_id")UUID id);

    @GetMapping
    ResponseEntity<GetPaymentResponse> getPayment(@RequestParam(name = "payment_id")UUID id);

    @PostMapping
    ResponseEntity<CreatePaymentResponse> sendPayment(@RequestBody @Valid CreatePaymentRequest request); // @Valid — нужна, если поле — вложенный объект с валидацией.
}
