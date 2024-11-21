package com.trannubichthai.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product")
@Builder
@Setting(settingPath = "/es-settings.json")
public class ProductModel implements Serializable {
    @Id
    private UUID id;
    private String name;
    private BigDecimal unitPrice;
    private String description;
    private String categoryName;
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;
    private String imageUrl;
}
