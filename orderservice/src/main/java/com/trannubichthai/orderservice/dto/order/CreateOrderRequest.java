package com.trannubichthai.orderservice.dto.order;

import com.trannubichthai.orderservice.dto.orderaddress.CreateOrderAddressRequest;
import com.trannubichthai.orderservice.dto.orderItem.CreateOrderItemRequest;
import lombok.Getter;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CreateOrderRequest {

    @NotNull
    private CreateOrderAddressRequest address;

    @NotNull
    private List<CreateOrderItemRequest> items;
}