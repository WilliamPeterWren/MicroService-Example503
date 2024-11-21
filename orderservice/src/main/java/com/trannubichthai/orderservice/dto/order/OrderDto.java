package com.trannubichthai.orderservice.dto.order;

import com.trannubichthai.orderservice.dto.orderaddress.OrderAddressDto;
import com.trannubichthai.orderservice.dto.orderItem.OrderItemDto;
import com.trannubichthai.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class OrderDto {
    private UUID id;
    private UUID customerId;
    private OrderAddressDto address;
    private List<OrderItemDto> items;
    private OrderStatus orderStatus;
    private LocalDateTime createdDate;
}