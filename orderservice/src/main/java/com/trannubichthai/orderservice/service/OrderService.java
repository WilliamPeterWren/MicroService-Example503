package com.trannubichthai.orderservice.service;

// import com.trannubichthai.orderservice.client.InventoryServiceClient;
import com.trannubichthai.orderservice.dto.Pagination;
import com.trannubichthai.orderservice.dto.inventory.InventoryCheckRequest;
// import com.trannubichthai.orderservice.dto.inventory.InventoryCheckResponse;
import com.trannubichthai.orderservice.dto.order.OrderDto;
import com.trannubichthai.orderservice.dto.order.OrderMapper;
import com.trannubichthai.orderservice.dto.order.CreateOrderRequest;
// import com.trannubichthai.orderservice.exception.ProductNotInStockException;
import com.trannubichthai.orderservice.model.Order;
import com.trannubichthai.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    // private final InventoryServiceClient inventoryServiceClient;

    public OrderDto createOrder(CreateOrderRequest createOrderRequest) {
        Order order = orderMapper.orderRequestToOrder(createOrderRequest);
        validateOrder(order);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDto(savedOrder);
    }

    private void validateOrder(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }
    }

    public Pagination<OrderDto> getAllOrders(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Order> orders = orderRepository.findAll(paging);
        return new Pagination<>(orders.stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList()),
                orders.getTotalElements());
    }
}
