package com.trannubichthai.product_service.dto.product;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class ProductSearchDTO {
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private String categoryName;
    private String description;
    private LocalDateTime createdDate;
    private String imageUrl;
}
