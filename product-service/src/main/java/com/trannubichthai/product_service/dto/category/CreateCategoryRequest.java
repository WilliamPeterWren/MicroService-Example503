package com.trannubichthai.product_service.dto.category;

import lombok.Getter;
import jakarta.validation.constraints.NotNull;

@Getter
public class CreateCategoryRequest {
    @NotNull
    private String name;
}
