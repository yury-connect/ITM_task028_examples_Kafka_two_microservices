package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.src.model.entity.Payment;
import com.mycompany.userservice.src.model.kafka.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "createDate", expression = "java(trimToSeconds(entity.getCreateDate()))")
    PaymentDTO toDto(Payment entity);


    Payment toEntity(PaymentDTO dto);


    /**
     * Округляем дату-время до секунд
     * @param dateTime время с микросекундами
     * @return время без микросекунд
     */
    default LocalDateTime trimToSeconds(LocalDateTime dateTime) {
        return dateTime == null ? null : dateTime.withNano(0);
    }
}
