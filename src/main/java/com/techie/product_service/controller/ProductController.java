package com.techie.product_service.controller;

import com.techie.product_service.dto.ProductRequest;
import com.techie.product_service.dto.ProductResponse;
import com.techie.product_service.service.ProductService;
import com.techie.product_service.service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create product")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {

        return ResponseEntity.ok(productService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductResponse>> getPage(Pageable pageable) {

        return ResponseEntity.ok(
                productService.getPage(pageable)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam String name) {

        return ResponseEntity.ok(
                productService.searchByName(name)
        );
    }
}
