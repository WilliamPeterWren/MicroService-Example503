package com.trannubichthai.inventoryservice.controller;

import com.trannubichthai.inventoryservice.dto.CreateInventoryRequest;
import com.trannubichthai.inventoryservice.dto.InventoryCheckRequest;
import com.trannubichthai.inventoryservice.dto.InventoryCheckResponse;
import com.trannubichthai.inventoryservice.dto.InventoryDto;

import com.trannubichthai.inventoryservice.service.InventoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/v1/inventories")
@RestController
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/isInStock")
    public ResponseEntity<InventoryCheckResponse> isInStock(
            @RequestBody List<InventoryCheckRequest> inventoryCheckRequests) {
        return ResponseEntity.ok(inventoryService.isInStock(inventoryCheckRequests));
    }

    @GetMapping("/getAll")
    public List<InventoryDto> getInventory() {
        return inventoryService.getAllInventories();
    }

    @PostMapping("/create")
    public ResponseEntity<InventoryDto> createCategory(
            @Valid @RequestBody CreateInventoryRequest createInventoryRequest) {
        return new ResponseEntity<>(inventoryService.createInventory(createInventoryRequest), HttpStatus.CREATED);
    }

}
