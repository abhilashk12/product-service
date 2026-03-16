package com.techie.product_service.service;

import com.techie.product_service.dto.ProductRequest;
import com.techie.product_service.dto.ProductResponse;
import com.techie.product_service.entity.Product;
import com.techie.product_service.exception.ProductNotFoundException;
import com.techie.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {

        log.info("Creating product with name: {}", request.getName());

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setActive(true);
        product.setCreatedAt(LocalDateTime.now());

        productRepository.save(product);

        log.info("Product created with id: {}", product.getId());

        return mapToResponse(product);
    }

    @Override
    public ProductResponse getById(Long id) {

        log.info("Fetching product with id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return mapToResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        log.info("Updating product id: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(request.getCategory());
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);

        log.info("Product updated id: {}", id);

        return mapToResponse(product);
    }

    @Override
    public void delete(Long id) {

        log.info("Deleting product id: {}", id);

        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductResponse> getPage(Pageable pageable) {

        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public List<ProductResponse> searchByName(String name) {

        log.info("Searching products by name: {}", name);

        return productRepository
                .findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // ===== mapper =====

    private ProductResponse mapToResponse(Product product) {

        ProductResponse res = new ProductResponse();

        res.setId(product.getId());
        res.setName(product.getName());
        res.setDescription(product.getDescription());
        res.setPrice(product.getPrice());
        res.setStock(product.getStock());
        res.setActive(product.getActive());
        res.setCategory(product.getCategory());

        return res;
    }

}