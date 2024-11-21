package com.trannubichthai.inventoryservice.repository;

import com.trannubichthai.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    Inventory findByProductIdAndQuantityLessThan(UUID productId, Integer quantity);

    Inventory getByProductId(UUID productId);

    Long deleteByProductId(UUID productId);
}
