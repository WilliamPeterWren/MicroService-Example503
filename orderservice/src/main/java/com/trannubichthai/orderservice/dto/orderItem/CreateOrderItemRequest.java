package com.trannubichthai.orderservice.dto.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderItemRequest {
    @NotNull
    private UUID productId;
    @NotNull
    private Integer quantity;
}