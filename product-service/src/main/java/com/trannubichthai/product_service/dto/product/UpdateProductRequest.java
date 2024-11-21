package com.trannubichthai.product_service.dto.product;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal unitPrice;
    @NotNull
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private String imageUrl;
}
