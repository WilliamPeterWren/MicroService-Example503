package com.trannubichthai.product_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.trannubichthai.product_service.dto.Pagination;
import com.trannubichthai.product_service.dto.ProductDTO;
import com.trannubichthai.product_service.dto.comment.CommentDTO;
import com.trannubichthai.product_service.dto.product.CreateProductRequest;
// import com.trannubichthai.product_service.dto.product.ProductSearchDTO;
import com.trannubichthai.product_service.dto.product.UpdateProductRequest;
import com.trannubichthai.product_service.service.ProductService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductDTOById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductDTOById(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByProductId(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getCommentsByProductId(id));
    }

    @GetMapping("/findByIds/{productIds}")
    public ResponseEntity<List<ProductDTO>> getProductsByIds(@PathVariable List<UUID> productIds) {
        return ResponseEntity.ok(productService.getProductsByIds(productIds));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return new ResponseEntity<>(productService.createProduct(createProductRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(
            @Valid @RequestBody UpdateProductRequest updateProductRequest,
            @PathVariable UUID id) {
        return ResponseEntity.ok(productService.updateProduct(updateProductRequest, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<UUID> deleteProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    // Search products by term, page, size, and other filters
    // @GetMapping
    // public ResponseEntity<List<ProductSearchDTO>> getProductBySearch(
    // @RequestParam(required = false, defaultValue = "") String searchTerm,
    // @RequestParam(required = false, defaultValue = "0") int page,
    // @RequestParam(required = false, defaultValue = "10") int size,
    // @RequestParam(required = false, defaultValue = "dateAsc") String sort,
    // @RequestParam(required = false, defaultValue = "") String filter) {
    // return ResponseEntity.ok(productService.searchProduct(searchTerm, page, size,
    // sort, filter));
    // }

    // Get paginated list of products
    @GetMapping("/getAll")
    public ResponseEntity<Pagination<ProductDTO>> getProductByPagination(
            @RequestParam(required = false, defaultValue = "0") int pageNo,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(productService.getAllProducts(pageNo, pageSize));
    }
}
