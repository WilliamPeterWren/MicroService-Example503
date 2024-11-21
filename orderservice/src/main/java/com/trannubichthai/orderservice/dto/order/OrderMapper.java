package com.trannubichthai.orderservice.dto.order;

import com.trannubichthai.orderservice.dto.orderaddress.OrderAddressMapper;
import com.trannubichthai.orderservice.dto.orderItem.OrderItemMapper;
import com.trannubichthai.orderservice.model.Order;
import com.trannubichthai.orderservice.model.OrderAddress;
import com.trannubichthai.orderservice.model.OrderItem;
import com.trannubichthai.orderservice.model.OrderStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
        private final OrderAddressMapper orderAddressMapper;
        private final OrderItemMapper orderItemMapper;

        public OrderDto orderToOrderDto(Order order) {
                return OrderDto.builder()
                                .id(order.getId())
                                .customerId(order.getCustomerId())
                                .orderStatus(order.getOrderStatus())
                                .address(orderAddressMapper.orderAddressToOrderAddressDto(order.getAddress()))
                                .items(order.getItems()
                                                .stream()
                                                .map(orderItemMapper::orderToOrderItemDto)
                                                .collect(Collectors.toList()))
                                .createdDate(order.getCreatedDate())
                                .build();
        }

        public Order orderRequestToOrder(CreateOrderRequest createOrderRequest) {
                UUID customerId = UUID.fromString((String) SecurityContextHolder.getContext()
                                .getAuthentication().getPrincipal());

                Order order = Order.builder()
                                .customerId(customerId)
                                .orderStatus(OrderStatus.PENDING)
                                .build();

                OrderAddress address = orderAddressMapper
                                .orderAddressRequestToOrderAddress(createOrderRequest.getAddress());
                address.setOrder(order);
                order.setAddress(address);

                List<OrderItem> items = createOrderRequest.getItems()
                                .stream()
                                .map(itemRequest -> {
                                        OrderItem item = orderItemMapper.orderItemRequestToOrderItem(itemRequest);
                                        item.setOrder(order);
                                        return item;
                                })
                                .collect(Collectors.toList());
                order.setItems(items);

                return order;
        }

}