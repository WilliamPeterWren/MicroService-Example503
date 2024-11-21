package com.trannubichthai.orderservice.controller;

import com.trannubichthai.orderservice.dto.Pagination;
import com.trannubichthai.orderservice.dto.order.OrderDto;
import com.trannubichthai.orderservice.dto.order.CreateOrderRequest;
import com.trannubichthai.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
@Slf4j
// @Controller
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        log.info("create order request was called");
        return new ResponseEntity<>(orderService.createOrder(createOrderRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Pagination<OrderDto>> getAll(@RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(orderService.getAllOrders(pageNo, pageSize));
    }

}