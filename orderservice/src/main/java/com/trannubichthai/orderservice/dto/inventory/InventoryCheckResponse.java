package com.trannubichthai.orderservice.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@ToString
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryCheckResponse {
    List<UUID> isNotInStockProductIds;
    Boolean isInStock;
}