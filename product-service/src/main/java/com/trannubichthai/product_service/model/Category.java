package com.trannubichthai.product_service.model;

import com.trannubichthai.common.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity(name = "categories")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public class Category extends BaseModel {

    @Id
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
