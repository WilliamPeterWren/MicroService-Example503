package com.trannubichthai.product_service.service;

import com.trannubichthai.amqp.rabbit.RabbitMQMessageProducer;
import com.trannubichthai.amqp.dto.DeleteInventoryRequest;
import com.trannubichthai.product_service.dto.Pagination;
import com.trannubichthai.product_service.dto.ProductDTO;
import com.trannubichthai.product_service.dto.comment.CommentDTO;
import com.trannubichthai.product_service.dto.comment.CommentMapper;
import com.trannubichthai.product_service.dto.product.*;
import com.trannubichthai.product_service.exception.ProductNotFoundException;
import com.trannubichthai.product_service.model.Category;
import com.trannubichthai.product_service.model.Product;
// import com.trannubichthai.product_service.repository.ProductElasticRepository;
import com.trannubichthai.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;
    private final CommentMapper commentMapper;
    // private final ProductElasticRepository productElasticRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final ElasticsearchOperations elasticsearchOperations;

    public Pagination<ProductDTO> getAllProducts(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Product> products = productRepository.findAll(paging);
        return new Pagination<>(
                products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList()),
                products.getTotalElements());
    }

    public ProductDTO getProductDTOById(UUID id) {
        return productMapper.productToProductDTO(productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id: {} could not be found!", id);
                    throw new ProductNotFoundException("Product with id " + id + " could not be found!");
                }));
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id: {} could not be found!", id);
                    throw new ProductNotFoundException("Product with id " + id + " could not be found!");
                });
    }

    public List<CommentDTO> getCommentsByProductId(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product with id: {} could not be found!", id);
                    throw new ProductNotFoundException("Product with id " + id + " could not be found!");
                });
        return product.getComments().stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
    }

    public List<ProductDTO> getProductsByIds(List<UUID> productIds) {
        List<Product> products = productRepository.findByIdIn(productIds);
        return products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO createProduct(CreateProductRequest createProductRequest) {
        Category category = categoryService.getCategoryById(createProductRequest.getCategoryId());
        Product product = Product.builder()
                .name(createProductRequest.getName())
                .unitPrice(createProductRequest.getUnitPrice())
                .description(createProductRequest.getDescription())
                .category(category)
                .imageUrl(createProductRequest.getImageUrl())
                .build();

        Product savedProduct = productRepository.save(product);

        // InventoryRequest inventoryRequest = new
        // InventoryRequest(savedProduct.getId(),
        // createProductRequest.getQuantityInStock());
        // rabbitMQMessageProducer.publish(inventoryRequest, "inventory.exchange",
        // "create.inventory.routing-key");

        // saveProductToElastic(savedProduct);
        return productMapper.productToProductDTO(savedProduct);
    }

    public ProductDTO updateProduct(UpdateProductRequest updateProductRequest, UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.error("Product with id: {} could not be found!", productId);
                    throw new ProductNotFoundException("Product with id " + productId + " could not be found!");
                });
        Category category = categoryService.getCategoryById(updateProductRequest.getCategoryId());
        product.setCategory(category);
        product.setDescription(updateProductRequest.getDescription());
        product.setName(updateProductRequest.getName());
        product.setUnitPrice(updateProductRequest.getUnitPrice());
        product.setImageUrl(updateProductRequest.getImageUrl());
        productRepository.save(product);

        // updateProductFromElastic(product);
        return productMapper.productToProductDTO(product);
    }

    @Transactional
    public UUID deleteProduct(UUID id) {
        productRepository.deleteById(id);
        // productElasticRepository.deleteById(id);

        // DeleteInventoryRequest deleteInventoryRequest = new
        // DeleteInventoryRequest(id);
        // rabbitMQMessageProducer.publish(deleteInventoryRequest, "inventory.exchange",
        // "delete.inventory.routing-key");

        return id;
    }

    // public List<ProductSearchDTO> searchProduct(String searchTerm, int page, int
    // size, String sort, String filter) {
    // QueryBuilder queryBuilder = searchTerm == null || searchTerm.length() == 0
    // ? QueryBuilders.matchAllQuery()
    // : QueryBuilders.multiMatchQuery(searchTerm)
    // .field("name")
    // .field("categoryName")
    // .field("description")
    // .operator(Operator.AND)
    // .fuzziness(Fuzziness.TWO)
    // .prefixLength(0);

    // BoolQueryBuilder filterBuilder = QueryBuilders.boolQuery();
    // if (filter != null && filter.length() != 0) {
    // filterBuilder.filter(QueryBuilders.matchQuery("categoryName", filter));
    // }

    // NativeSearchQuery query = new NativeSearchQueryBuilder()
    // .withQuery(queryBuilder)
    // .withSort(SortBuilders.fieldSort(sort.getField()).order(sort.getOrder()))
    // .withPageable(PageRequest.of(page, size))
    // .withFilter(filterBuilder)
    // .build();

    // List<SearchHit<ProductModel>> productModels =
    // elasticsearchOperations.search(query, ProductModel.class,
    // IndexCoordinates.of("product"))
    // .getSearchHits();

    // return
    // productModels.stream().map(productMapper::productSearchDTOMapper).collect(Collectors.toList());
    // }

    // private void saveProductToElastic(Product product) {
    // ProductModel productModel = ProductModel.builder()
    // .categoryName(product.getCategory().getName())
    // .description(product.getDescription())
    // .id(product.getId())
    // .name(product.getName())
    // .unitPrice(product.getUnitPrice())
    // .createdDate(LocalDateTime.now())
    // .imageUrl(product.getImageUrl())
    // .build();
    // System.out.println(productModel);
    // productElasticRepository.save(productModel);
    // }

    // private void updateProductFromElastic(Product product) {
    // ProductModel productModel = ProductModel.builder()
    // .categoryName(product.getCategory().getName())
    // .description(product.getDescription())
    // .id(product.getId())
    // .name(product.getName())
    // .unitPrice(product.getUnitPrice())
    // .imageUrl(product.getImageUrl())
    // .build();
    // productElasticRepository.save(productModel);
    // }
}
