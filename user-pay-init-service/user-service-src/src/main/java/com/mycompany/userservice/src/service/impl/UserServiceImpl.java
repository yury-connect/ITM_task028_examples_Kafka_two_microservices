package com.mycompany.userservice.src.service.impl;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.PaymentTransactional;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import com.mycompany.userservice.rest.enums.PaymentTransactionalStatus;
import com.mycompany.userservice.rest.response.GetPaymentTransactionalUserResponse;
import com.mycompany.userservice.src.exception.NotFoundException;
import com.mycompany.userservice.src.repository.PaymentTransactionalRepository;
import com.mycompany.userservice.src.service.UserService;
import com.mycompany.userservice.src.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Реализация UserService.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PaymentTransactionalRepository paymentTransactionalRepository;


    public CreatePaymentUserResponse sendPayment(User user, Money payment) {
        PaymentTransactional paymentId111 = new PaymentTransactional();

        // тут будет логика отправки платежа в Kafka, пока мешаю заглушку
        UUID paymentKafkaId = UUID.randomUUID(); // Получим когда отправим в Kafka

        // тут будет логика сохранения платежа в месную БД, пока мешаю заглушку
        UUID paymentId = UUID.randomUUID(); // Получим когда сохраним в БД

        CreatePaymentUserResponse response = CreatePaymentUserResponse.builder()
                .paymentTransactionalId(paymentId)
                .status(PaymentTransactionalStatus.UNDEFINED)
                .build();

        return response;
    }

    @Override
    public GetPaymentStatusUserResponse getStatusSendingPayment(UUID id) {

        // тут будет логика получения статуса, пока мешаю заглушку
        var status = PaymentTransactionalStatus.UNDEFINED;

        GetPaymentStatusUserResponse response = GetPaymentStatusUserResponse.builder()
                .status(status)
                .build();
        return response;
    }

    @Override
    public GetPaymentTransactionalUserResponse getPaymentById(UUID id) {

        PaymentTransactional payment = paymentTransactionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));

        GetPaymentTransactionalUserResponse result = GetPaymentTransactionalUserResponse.builder()
                .userName(payment.getUser().getUserName())
                .coin(payment.getMoney().getCoin())
                .currency(payment.getMoney().getCurrency())
                .build();

        return result;
    }
}
