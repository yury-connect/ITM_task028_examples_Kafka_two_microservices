package com.mycompany.userservice.rest.enums;

public enum PaymentStatus {
    UNDEFINED,  // Невозможно определить статус
    NOT_FOUND,  // Оплата не найдена
    OK,         // Оплата отослана успешно
    ERROR       // Ошибка при отправке оплаты
}
