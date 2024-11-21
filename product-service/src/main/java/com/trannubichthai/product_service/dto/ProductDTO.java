package com.trannubichthai.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.trannubichthai.product_service.dto.category.CategoryDTO;
import com.trannubichthai.product_service.dto.comment.CommentDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private CategoryDTO category;
    private String description;
    private LocalDateTime createdDate;
    private String imageUrl;
    private List<CommentDTO> comments;
}
