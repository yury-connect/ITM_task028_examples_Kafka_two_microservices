package com.mycompany.userservice.src.controller;

import com.mycompany.userservice.rest.controller.UserController;
import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentTransactionalUserResponse;
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
    public ResponseEntity<GetPaymentStatusUserResponse> getStatusSendingPayment(@RequestParam(name = "payment_id") UUID id) {
        System.out.println("UserControllerImpl.getStatusSendingPayment: id = " + id);

        log.info("UserControllerImpl.getStatusSendingPayment: id = {}", id);

        var result = userService.getStatusSendingPayment(id);

        log.info("UserControllerImpl.getStatusSendingPayment: status = {}", result);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/payment_transactions")
    public ResponseEntity<GetPaymentTransactionalUserResponse> getPaymentById(@RequestParam(name = "payment_id") UUID id) {
        System.out.println("UserControllerImpl.getPaymentById: id = " + id);

        log.info("UserControllerImpl.getPaymentById: id = {}", id);

        var result = userService.getPaymentById(id);

        log.info("UserControllerImpl.getPaymentById: status = {}", result);
        return ResponseEntity.ok(result);
    }


    @Override
    @PostMapping("/payment_transactions")
    public ResponseEntity<CreatePaymentUserResponse> sendPayment(@RequestBody @Valid CreatePaymentUserRequest request) {
        System.out.println("UserControllerImpl.sendPayment: request = " + request);

        log.info("UserControllerImpl.sendPayment: request = {}", request);

        var result = userService.sendPayment(request.getUser(), request.getMoney());

        log.info("UserControllerImpl.sendPayment: response = {}", result);
        return ResponseEntity.ok(result);
    }
}
