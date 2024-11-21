package com.trannubichthai.orderservice.client;

import com.trannubichthai.orderservice.dto.inventory.InventoryCheckRequest;
import com.trannubichthai.orderservice.dto.inventory.InventoryCheckResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "inventoryservice", path = "/v1/inventories")
public interface InventoryServiceClient {

    @PostMapping("/isInStock")
    InventoryCheckResponse isInStock(List<InventoryCheckRequest> inventoryCheckRequest);
}
