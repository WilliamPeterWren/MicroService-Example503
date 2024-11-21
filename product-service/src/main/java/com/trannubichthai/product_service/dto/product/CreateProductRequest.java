package com.trannubichthai.product_service.dto.product;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateProductRequest {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    @NotNull
    private Long categoryId;
    @NotNull
    private String description;
    @NotNull
    private Integer quantityInStock;
    @NotNull
    private String imageUrl;
}
