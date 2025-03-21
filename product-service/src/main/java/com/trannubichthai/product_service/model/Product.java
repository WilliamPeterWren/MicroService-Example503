package com.trannubichthai.product_service.model;

import com.trannubichthai.common.model.AdvanceBaseModal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("deprecation")
@Entity(name = "products")
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Product extends AdvanceBaseModal {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    private BigDecimal unitPrice;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean deleted;

    private String imageUrl;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

}
