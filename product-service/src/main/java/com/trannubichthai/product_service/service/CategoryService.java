package com.trannubichthai.product_service.service;

import com.trannubichthai.product_service.dto.category.CategoryDTO;
import com.trannubichthai.product_service.dto.category.CategoryMapper;
import com.trannubichthai.product_service.dto.category.CreateCategoryRequest;
import com.trannubichthai.product_service.exception.CategoryNotFoundException;
import com.trannubichthai.product_service.model.Category;
import com.trannubichthai.product_service.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Category with id: {} could not be found!", id);
                    throw new CategoryNotFoundException("Category with id " + id + " could not be found!");
                });
    }

    public CategoryDTO createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = Category.builder()
                .name(createCategoryRequest.getName())
                .build();

        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO) // Use the correct method name
                .collect(Collectors.toList());
    }
}
