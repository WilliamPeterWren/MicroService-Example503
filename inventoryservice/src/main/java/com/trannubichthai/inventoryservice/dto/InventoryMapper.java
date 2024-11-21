package com.trannubichthai.inventoryservice.dto;

import com.trannubichthai.amqp.dto.InventoryRequest;
import com.trannubichthai.inventoryservice.model.Inventory;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryMapper {
    public Inventory createInventoryRequestToInventory(InventoryRequest inventoryRequest) {
        return Inventory.builder()
                .productId(inventoryRequest.getProductId())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }

    public InventoryDto inventoryToInventoryDTO(Inventory inventory) {
        return InventoryDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProductId())
                .quantity(inventory.getQuantity())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}