package com.trannubichthai.product_service.enumeration;

import co.elastic.clients.elasticsearch._types.SortOrder;
import lombok.Getter;

@Getter
public enum Sort {
    DATE_DESC("createdDate", SortOrder.Desc),
    DATE_ASC("createdDate", SortOrder.Asc),
    PRICE_DESC("unitPrice", SortOrder.Desc),
    PRICE_ASC("unitPrice", SortOrder.Asc);

    private final String field;
    private final SortOrder order;

    Sort(String field, SortOrder order) {
        this.field = field;
        this.order = order;
    }
}
