package com.trannubichthai.product_service.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pagination<T> {
    private List<T> data;
    private long totalSize;
}
