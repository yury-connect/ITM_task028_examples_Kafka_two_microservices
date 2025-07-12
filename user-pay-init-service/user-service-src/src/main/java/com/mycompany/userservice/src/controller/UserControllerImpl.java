package com.mycompany.userservice.src.controller;

import com.mycompany.userservice.rest.controller.UserController;
import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import com.mycompany.userservice.src.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Имплементация REST-контроллер для User Service.
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;


    @Override
    public ResponseEntity<CreatePaymentUserResponse> sendPayment(@RequestBody CreatePaymentUserRequest request) {
        log.info("UserControllerImpl.sendPayment: request = {}", request);

        var result = userService.sendPayment(request.getUser(), request.getMoney());

        log.info("UserControllerImpl.sendPayment: response = {}", result);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<GetPaymentStatusUserResponse> getStatusSendingPayment(@RequestParam UUID id) {
        log.info("UserControllerImpl.getStatusSendingPayment: id = {}", id);

        var result = userService.getStatusSendingPayment(id);

        log.info("UserControllerImpl.getStatusSendingPayment: status = {}", result);
        return ResponseEntity.ok(result);
    }
}
