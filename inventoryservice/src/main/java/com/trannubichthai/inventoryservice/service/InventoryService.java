package com.trannubichthai.inventoryservice.service;

import com.trannubichthai.amqp.dto.DeleteInventoryRequest;
import com.trannubichthai.amqp.dto.InventoryRequest;
import com.trannubichthai.inventoryservice.dto.CreateInventoryRequest;
import com.trannubichthai.inventoryservice.dto.InventoryCheckRequest;
import com.trannubichthai.inventoryservice.dto.InventoryCheckResponse;
import com.trannubichthai.inventoryservice.dto.InventoryDto;
import com.trannubichthai.inventoryservice.dto.InventoryMapper;
import com.trannubichthai.inventoryservice.model.Inventory;
import com.trannubichthai.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale.Category;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public void addProductToInventory(InventoryRequest inventoryRequest) {
        inventoryRepository.save(inventoryMapper.createInventoryRequestToInventory(inventoryRequest));
    }

    @Transactional
    public void deleteProductFromInventory(DeleteInventoryRequest deleteInventoryRequest) {
        inventoryRepository.deleteByProductId(deleteInventoryRequest.getProductId());
    }

    public void updateProductFromInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = inventoryRepository.getByProductId(inventoryRequest.getProductId());
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventoryRepository.save(inventory);
    }

    public InventoryCheckResponse isInStock(List<InventoryCheckRequest> inventoryCheckRequests) {
        List<Inventory> inventories = inventoryCheckRequests.stream().map(inventoryRequest -> inventoryRepository
                .findByProductIdAndQuantityLessThan(inventoryRequest.getProductId(), inventoryRequest.getQuantity()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<UUID> inventorIds = inventories.stream()
                .map(Inventory::getProductId).collect(Collectors.toList());

        return InventoryCheckResponse.builder()
                .isNotInStockProductIds(inventorIds)
                .isInStock(inventorIds.size() == 0)
                .build();
    }

    // added by pt
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(inventoryMapper::inventoryToInventoryDTO).collect(Collectors.toList());
    }

    public InventoryDto createInventory(CreateInventoryRequest createInventoryRequest) {
        Inventory inventory = Inventory.builder()
                .productId(createInventoryRequest.getProductId())
                .quantity(createInventoryRequest.getQuantity())
                .build();

        return inventoryMapper.inventoryToInventoryDTO(inventoryRepository.save(inventory));
    }

}