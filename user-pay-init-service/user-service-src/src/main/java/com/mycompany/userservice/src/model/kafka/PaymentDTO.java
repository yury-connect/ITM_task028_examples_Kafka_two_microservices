package com.mycompany.userservice.src.model.kafka;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.userservice.src.model.entity.Money;
import com.mycompany.userservice.src.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Сущность для передачи платежа через Kafka. Необходимо для преобразования в JSON.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDTO {

    @NotNull
    private UUID id;

    @NotNull
    private Money money;

    @NotNull
    private User user;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // убираем микросекунды и тайм-зону
    private LocalDateTime createDate;
}
