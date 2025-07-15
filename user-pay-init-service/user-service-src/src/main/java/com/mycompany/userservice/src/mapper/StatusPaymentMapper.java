package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.src.model.StatusPaymentEntity;
import com.mycompany.userservice.src.model.kafka.StatusPaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StatusPaymentMapper {

    @Mapping(target = "id", ignore = true) // игнорируем id при маппинге
    @Mapping(target = "createDate", ignore = true) // игнорируем createDate при маппинге
    StatusPaymentEntity toEntity(StatusPaymentDTO dto);

    StatusPaymentDTO toDto(StatusPaymentEntity entity);
}
