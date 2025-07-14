package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.rest.model.User;
import com.mycompany.userservice.rest.request.CreatePaymentRequest;
import com.mycompany.userservice.src.exception.ValidationException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface CreatePaymentRequestToUserMapper {

    @Mapping(source = "userName", target = "userName", qualifiedByName = "validateUserName")
    User toUser(CreatePaymentRequest request);

    @Named("validateUserName")
    static String validateUserName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("Имя пользователя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException(
                    "Имя пользователя '" + name + "' слишком короткое. Минимальная длинна = 3 символа");
        }
        if (name.length() > 32) {
            throw new IllegalArgumentException(
                    "Имя пользователя ''" + name + "' слишком длинное. Максимальная длинна = 32 символf");
        }
        return name.trim();
    }
}