package com.trannubichthai.product_service.dto.product;

import org.springframework.stereotype.Component;

import com.trannubichthai.product_service.dto.ProductDTO;
import com.trannubichthai.product_service.dto.category.CategoryMapper;
import com.trannubichthai.product_service.dto.comment.CommentMapper;
import com.trannubichthai.product_service.model.Product;
import com.trannubichthai.product_service.model.ProductModel;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import org.springframework.data.elasticsearch.core.SearchHit;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final CategoryMapper categoryMapper;
    private final CommentMapper commentMapper;

    public ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .id(product.getId())
                .unitPrice(product.getUnitPrice())
                .description(product.getDescription())
                .category(categoryMapper.categoryToCategoryDTO(product.getCategory()))
                .createdDate(product.getCreatedDate())
                .imageUrl(product.getImageUrl())
                .comments(product.getComments().stream()
                        .map(commentMapper::commentToCommentDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public ProductSearchDTO productSearchDTOMapper(SearchHit<ProductModel> productModel) {
        return ProductSearchDTO.builder()
                .name(productModel.getContent().getName())
                .id(productModel.getContent().getId())
                .unitPrice(productModel.getContent().getUnitPrice())
                .description(productModel.getContent().getDescription())
                .categoryName(productModel.getContent().getCategoryName())
                .createdDate(productModel.getContent().getCreatedDate())
                .imageUrl(productModel.getContent().getImageUrl())
                .build();
    }
}
