package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.rest.enums.Currency;
import com.mycompany.userservice.rest.model.Money;
import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.src.exception.ValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface CreatePaymentRequestToMoneyMapper {

    @Mapping(source = "amount", target = "amount")
    @Mapping(source = "currency", target = "currency", qualifiedByName = "mapCurrencySafe")
    Money toMoney(CreatePaymentRequest request);

    /**
     * Безопасная обработка невалидных значений
     * @param currencyName наименование валюты (должно соответствовать перечислению.)
     * @return валюта из enum Currency
     */
    @Named("mapCurrencySafe")
    default Currency mapCurrencySafe(String currencyName) {
        if (currencyName == null || currencyName.isBlank()) {
            throw new ValidationException("Поле валюты 'currency' не может быть пустым!");
        }

        try {
            return Currency.valueOf(currencyName.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ValidationException("Валюта '" + currencyName + "' (поле 'currency') не найдена!", ex);
        }
    }
}
