package com.trannubichthai.inventoryservice.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateInventoryRequest {
    private UUID productId;
    private Integer quantity;
}