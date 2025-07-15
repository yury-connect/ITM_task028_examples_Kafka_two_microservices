package com.mycompany.userservice.src.service.impl;

import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.model.Payment;
import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import com.mycompany.userservice.rest.enums.PaymentStatus;
import com.mycompany.userservice.rest.response.GetPaymentResponse;
import com.mycompany.userservice.src.exception.NotFoundException;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToMoneyMapper;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToUserMapper;
import com.mycompany.userservice.src.mapper.PaymentStatusToGetPaymentStatusResponseMapper;
import com.mycompany.userservice.src.repository.MoneyRepository;
import com.mycompany.userservice.src.repository.PaymentRepository;
import com.mycompany.userservice.src.service.UserService;
import com.mycompany.userservice.src.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MoneyRepository moneyRepository;
    private final PaymentRepository paymentRepository;
    private final CreatePaymentRequestToUserMapper userMapper;
    private final CreatePaymentRequestToMoneyMapper moneyMapper;
    private final PaymentStatusToGetPaymentStatusResponseMapper statusMapper;

    private final KafkaTemplate<String, Payment> kafkaTemplate;
    private static final String TOPIC = "payments";

    public CreatePaymentResponse sendPayment(CreatePaymentRequest request) {

        User userReceived = userMapper.toUser(request);
        Money moneyReceived = moneyMapper.toMoney(request);

        User user = userRepository.findByUserName(userReceived.getUserName())
                .orElseGet(() -> userRepository.save(userReceived));
        Money money = moneyRepository.save(moneyReceived);

        Payment payment  =
                paymentRepository.save(
                        Payment.builder()
                                .money(money)
                                .user(user)
                                .build()
                );



        CompletableFuture<SendResult<String, Payment>> future =
                kafkaTemplate.send(TOPIC, payment); // отправка платежа в Kafka -тут скорее всего покрутить придется с payment



        CreatePaymentResponse response = CreatePaymentResponse.builder()
                .id(payment.getId())
                .status(getStatusPaymentKafka(payment.getId())) // тут будет статус отправки платежа в Kafka
                .build();

        return response;
    }

    /**
     * Вернет из Kafka статус платежа по id платежа
     * Статусом платежа управляет другой сервис.
     * Сразу после создания платежа статус будет PROCESSING
     * @param id
     * @return
     */
    private PaymentStatus getStatusPaymentKafka(UUID id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));

        // тут будет логика получения статуса, пока мешаю заглушку
        PaymentStatus status = PaymentStatus.PROCESSING;

        return status;
    }



    @Override
    public GetStatusPaymentResponse getStatusPayment(UUID id) {
        return statusMapper.toGetStatusPaymentResponse(getStatusPaymentKafka(id));
    }



    @Override
    public GetPaymentResponse getPayment(UUID id) {

        Payment payment = paymentRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));
                .orElseThrow(() -> new NotFoundException(
                        "Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));

        GetPaymentResponse result = GetPaymentResponse.builder()
                .userName(payment.getUser().getUserName())
                .coin(payment.getMoney().getAmount())
                .currency(payment.getMoney().getCurrency())
                .build();

        return result;
    }
}
