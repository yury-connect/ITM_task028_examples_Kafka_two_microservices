package com.mycompany.userservice.src.mapper;

import com.mycompany.userservice.rest.enums.StatusPayment;
import com.mycompany.userservice.rest.response.GetStatusPaymentResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface PaymentStatusToGetPaymentStatusResponseMapper {

    default GetStatusPaymentResponse toGetStatusPaymentResponse(StatusPayment status) {
        return new GetStatusPaymentResponse(status);
    }
}