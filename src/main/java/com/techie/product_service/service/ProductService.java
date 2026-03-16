package com.techie.product_service.service;

import com.techie.product_service.dto.ProductRequest;
import com.techie.product_service.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);

    Page<ProductResponse> getPage(Pageable pageable);

    List<ProductResponse> searchByName(String name);

}
