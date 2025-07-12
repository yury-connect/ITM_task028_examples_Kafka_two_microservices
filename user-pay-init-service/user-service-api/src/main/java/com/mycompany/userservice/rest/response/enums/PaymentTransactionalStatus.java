package com.mycompany.userservice.rest.response.enums;

public enum PaymentTransactionalStatus {
    UNDEFINED,  // Невозможно определить статус
    NOT_FOUND,  // Оплата не найдена
    OK,         // Оплата отослана успешно
    ERROR       // Ошибка при отправке оплаты
}
