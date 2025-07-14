package com.mycompany.userservice.src.service.impl;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.Payment;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusResponse;
import com.mycompany.userservice.rest.enums.PaymentStatus;
import com.mycompany.userservice.rest.response.GetPaymentResponse;
import com.mycompany.userservice.src.exception.NotFoundException;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToMoneyMapper;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToUserMapper;
import com.mycompany.userservice.src.repository.MoneyRepository;
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
    private final MoneyRepository moneyRepository;
    private final PaymentTransactionalRepository paymentTransactionalRepository;
    private final CreatePaymentRequestToUserMapper userMapper;
    private final CreatePaymentRequestToMoneyMapper moneyMapper;


    public CreatePaymentResponse sendPayment(CreatePaymentRequest request) {

        User userReceived = userMapper.toUser(request);
        Money moneyReceived = moneyMapper.toMoney(request);

        User user = userRepository.findByUserName(userReceived.getUserName())
                .orElseGet(() -> userRepository.save(userReceived));
        Money money = moneyRepository.save(moneyReceived);

        Payment payment  =
                paymentTransactionalRepository.save(
                        Payment.builder()
                                .money(money)
                                .user(user)
                                .build()
                );
        UUID paymentId = payment.getId(); // С этим-же id будем отправлять платеж в Kafka для проводки.

        // тут будет логика отправки платежа в Kafka, пока мешаю заглушку
        UUID paymentKafkaId = UUID.randomUUID(); // Получим когда отправим в Kafka

        CreatePaymentResponse response = CreatePaymentResponse.builder()
                .id(paymentId)
                .status(PaymentStatus.UNDEFINED) // тут будет статус отправки платежа в Kafka
                .build();

        return response;
    }















    @Override
    public GetPaymentStatusResponse getStatusSendingPayment(UUID id) {

        // тут будет логика получения статуса, пока мешаю заглушку
        var status = PaymentStatus.UNDEFINED;

        GetPaymentStatusResponse response = GetPaymentStatusResponse.builder()
                .status(status)
                .build();
        return response;
    }

    @Override
    public GetPaymentResponse getPaymentById(UUID id) {

        Payment payment = paymentTransactionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));

        GetPaymentResponse result = GetPaymentResponse.builder()
                .userName(payment.getUser().getUserName())
                .coin(payment.getMoney().getAmount())
                .currency(payment.getMoney().getCurrency())
                .build();

        return result;
    }
}
