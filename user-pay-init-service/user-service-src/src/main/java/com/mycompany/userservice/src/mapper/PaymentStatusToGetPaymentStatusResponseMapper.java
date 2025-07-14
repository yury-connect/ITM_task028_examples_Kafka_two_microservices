package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.rest.enums.PaymentStatus;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PaymentStatusToGetPaymentStatusResponseMapper {

    default GetStatusPaymentResponse toGetStatusPaymentResponse(PaymentStatus status) {
        return new GetStatusPaymentResponse(status);
    }
}