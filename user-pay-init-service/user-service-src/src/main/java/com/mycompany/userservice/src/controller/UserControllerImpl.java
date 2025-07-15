package com.mycompany.userservice.src.controller;

import com.mycompany.userservice.rest.controller.UserController;
import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import com.mycompany.userservice.rest.response.GetPaymentResponse;
import com.mycompany.userservice.src.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Имплементация REST-контроллер для User Service.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;
    @Override
    @GetMapping("/payment_statuses")
    public ResponseEntity<GetStatusPaymentResponse> getStatusPayment(@RequestParam(name = "payment_id") UUID id) {
        log.info("UserControllerImpl.getStatusSendingPayment: id = {}", id);

        var result = userService.getStatusPayment(id);

        log.info("UserControllerImpl.getStatusSendingPayment: status = {}", result);
        return ResponseEntity.ok(result);
    }


    @Override
    @GetMapping("/payment_transactions")
    public ResponseEntity<GetPaymentResponse> getPayment(@RequestParam(name = "payment_id") UUID id) {
        log.info("UserControllerImpl.getPaymentById: id = {}", id);

        var result = userService.getPayment(id);

        log.info("UserControllerImpl.getPaymentById: status = {}", result);
        return ResponseEntity.ok(result);
    }


    @Override
    @PostMapping("/payment_transactions")
    public ResponseEntity<CreatePaymentResponse> sendPayment(@RequestBody @Valid CreatePaymentRequest request) {
        log.info("UserControllerImpl.sendPayment: request = {}", request);

        var result = userService.sendPayment(request);

        log.info("UserControllerImpl.sendPayment: response = {}", result);
        return ResponseEntity.ok(result);
    }
}
