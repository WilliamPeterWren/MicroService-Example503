package com.trannubichthai.product_service.dto;

import java.util.UUID;

import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InventoryDTO {
    private UUID productId;
    private Integer quantity;
}
