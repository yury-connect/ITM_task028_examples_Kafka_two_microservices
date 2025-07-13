package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.rest.model.PaymentTransactional;
import com.mycompany.userservice.rest.request.CreatePaymentUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Маппер «DTO ➜ Entity» для создания новой транзакции.
 */
@Mapper(componentModel = "spring")
public interface CreatePaymentUserRequestToPaymentTransactionalMapper {

    /**
     * Преобразует поступивший запрос в JPA‑сущность.
     *
     *  id             — игнорируем (сгенерируется в БД);
     *  money, user    — копируются как есть.
     */
    @Mapping(target = "id", ignore = true)
    PaymentTransactional toEntity(CreatePaymentUserRequest request);
}
