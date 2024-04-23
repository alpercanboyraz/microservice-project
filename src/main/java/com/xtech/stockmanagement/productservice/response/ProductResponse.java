package com.xtech.stockmanagement.productservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductResponse {
    private long productId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Long productCreatedDate;
    private Long productUpdatedDate;
}
