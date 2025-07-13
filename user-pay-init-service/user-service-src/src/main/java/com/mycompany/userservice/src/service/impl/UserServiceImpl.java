package com.mycompany.userservice.src.service.impl;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.PaymentTransactional;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import com.mycompany.userservice.rest.response.CreatePaymentUserResponse;
import com.mycompany.userservice.rest.response.GetPaymentStatusUserResponse;
import com.mycompany.userservice.rest.enums.PaymentTransactionalStatus;
import com.mycompany.userservice.rest.response.GetPaymentTransactionalUserResponse;
import com.mycompany.userservice.src.exception.NotFoundException;
import com.mycompany.userservice.src.mapper.CreatePaymentUserRequestToPaymentTransactionalMapper;
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
    private final CreatePaymentUserRequestToPaymentTransactionalMapper mapper;


    public CreatePaymentUserResponse sendPayment(CreatePaymentUserRequest request) {

        // сохранить объект в базу
        Money money = moneyRepository.save(request.getMoney()); // теперь у него есть ID
//        User user = userRepository.save(request.getUser()); // теперь у него есть ID // заменяем

        User user = userRepository.findByUserName(request.getUser().getUserName())
                .orElseGet(() -> userRepository.save(request.getUser()));

//        PaymentTransactional entity  = mapper.toEntity(request);
//        PaymentTransactional entitySaved  = paymentTransactionalRepository.save(entity);
        PaymentTransactional entitySaved  =
                paymentTransactionalRepository.save(
                        PaymentTransactional.builder()
                                .money(money)
                                .user(user)
                                .build()
                );
        UUID paymentId = entitySaved.getId();

        // тут будет логика отправки платежа в Kafka, пока мешаю заглушку
        UUID paymentKafkaId = UUID.randomUUID(); // Получим когда отправим в Kafka

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
