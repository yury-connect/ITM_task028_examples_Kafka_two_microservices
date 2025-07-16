package com.mycompany.userservice.src.service.impl;

import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.rest.response.CreatePaymentResponse;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import com.mycompany.userservice.rest.enums.StatusPayment;
import com.mycompany.userservice.rest.response.GetPaymentResponse;
import com.mycompany.userservice.src.config.KafkaProperties;
import com.mycompany.userservice.src.exception.NotFoundException;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToMoneyMapper;
import com.mycompany.userservice.src.mapper.CreatePaymentRequestToUserMapper;
import com.mycompany.userservice.src.mapper.PaymentMapper;
import com.mycompany.userservice.src.mapper.PaymentStatusToGetPaymentStatusResponseMapper;
import com.mycompany.userservice.src.model.entity.Money;
import com.mycompany.userservice.src.model.entity.Payment;
import com.mycompany.userservice.src.model.entity.StatusPaymentEntity;
import com.mycompany.userservice.src.model.entity.User;
import com.mycompany.userservice.src.model.kafka.PaymentDTO;
import com.mycompany.userservice.src.repository.MoneyRepository;
import com.mycompany.userservice.src.repository.PaymentRepository;
import com.mycompany.userservice.src.repository.StatusPaymentRepository;
import com.mycompany.userservice.src.service.UserService;
import com.mycompany.userservice.src.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MoneyRepository moneyRepository;
    private final PaymentRepository paymentRepository;
    private final StatusPaymentRepository statusPaymentRepository;
    private final CreatePaymentRequestToUserMapper userMapper;
    private final CreatePaymentRequestToMoneyMapper moneyMapper;
    private final PaymentStatusToGetPaymentStatusResponseMapper statusMapper;
    private final PaymentMapper paymentMapper;
    private final KafkaTemplate<String, PaymentDTO> kafkaTemplate;
    private final KafkaProperties kafkaProperties;


    @Transactional
    public CreatePaymentResponse sendPayment(CreatePaymentRequest request) {
        User userReceived = userMapper.toUser(request); // вытащим сущности из request
        Money moneyReceived = moneyMapper.toMoney(request);

        User user = userRepository.findByUserName(userReceived.getUserName()) // засунем их в базу
                .orElseGet(() -> userRepository.save(userReceived));
        Money money = moneyRepository.save(moneyReceived);
        UUID id = paymentRepository.save( // и получим id сохраненной сущности
                Payment.builder()
                        .money(money)
                        .user(user)
                        .build()
        ).getId();
        paymentRepository.flush(); // без ручной синхронизации дата в поле 'LocalDateTime createDate' Hibernate'ом не назначится сразу, а только при первой синхронизации

        PaymentDTO dto  = paymentMapper.toDto(paymentRepository.findById(id).orElseThrow(  // Сохраняя payment в базу и сразу найдем его, получим обратно с автоматически заполненными полями, если не смогли сохранить - кидаем ошибку
                () -> new NotFoundException(
                        "UserServiceImpl.sendPayment: Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND)));

        CompletableFuture<SendResult<String, PaymentDTO>> future =
                kafkaTemplate.send(kafkaProperties.getUserPaymentsTopic(), dto); // отправка платежа в Kafka, ниже проконтролим результат
        future.thenAccept(result -> { // тут проверить, если future = null, то что-то пошло не так
            log.info("UserServiceImpl.sendPayment: Сообщение отправлено в Kafka. Топик: {}, offset: {}",
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().offset());
        }).exceptionally(ex -> {
            log.error("UserServiceImpl.sendPayment: Ошибка при отправке сообщения в Kafka: {}", ex.getMessage(), ex);
            statusPaymentRepository.findByPaymentId(id).get().setStatusPayment(StatusPayment.ERROR); // ❗ Здесь можешь записать в БД статус ERROR
            statusPaymentRepository.flush();
            throw new NotFoundException(
                    "UserServiceImpl.sendPayment: Payment с id = " + id + " не отправлен в Kafka!", HttpStatus.SERVICE_UNAVAILABLE);
        });

        CreatePaymentResponse response = CreatePaymentResponse.builder()
                .id(dto.getId())
                .status(getStatusPaymentKafka(dto.getId())) // тут будет статус отправки платежа в Kafka
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
    private StatusPayment getStatusPaymentKafka(UUID id) {
        StatusPayment status = statusPaymentRepository.findByPaymentId(id)
                .map(StatusPaymentEntity::getStatusPayment)
                .orElse(StatusPayment.NOT_FOUND); // Заглушка, если статус ещё не пришёл
        return status;
    }



    @Override
    public GetStatusPaymentResponse getStatusPayment(UUID id) {
        return statusMapper.toGetStatusPaymentResponse(getStatusPaymentKafka(id));
    }



    @Override
    public GetPaymentResponse getPayment(UUID id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Payment with id = " + id + " not found!", HttpStatus.NOT_FOUND));

        GetPaymentResponse result = GetPaymentResponse.builder()
                .userName(payment.getUser().getUserName())
                .coin(payment.getMoney().getAmount())
                .currency(payment.getMoney().getCurrency())
                .build();

        return result;
    }


    /**
     * Для отладки выводим при старте значение топика
     */
    @PostConstruct
    public void checkTopic() {
        log.info("UserTopic from config: " + kafkaProperties.getUserPaymentsTopic());
        log.info("StatusPaymentsTopic from config: " + kafkaProperties.getUserStatusPaymentsTopic());
    }
}
