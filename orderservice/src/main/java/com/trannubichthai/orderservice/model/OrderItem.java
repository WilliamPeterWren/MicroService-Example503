package com.trannubichthai.orderservice.model;

import com.trannubichthai.common.model.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.*;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "order_items")
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderItem extends BaseModel {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    private UUID productId;

    private Integer quantity;
}