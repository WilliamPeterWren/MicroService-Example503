package com.trannubichthai.orderservice.dto.inventory;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Setter
@NoArgsConstructor
public class InventoryCheckRequest {
    UUID productId;
    Integer quantity;
}