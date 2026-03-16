package com.techie.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private Boolean active;

    private String category;

}