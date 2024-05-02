package com.example.springbootjava.controllers;

import com.example.springbootjava.services.ProductService;
import com.example.springbootjava.services.dtos.ProductDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.save(productDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(this.productService.update(id, productDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(Pageable pageable) {
        return ResponseEntity.ok(this.productService.findAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID id) {
        this.productService.delete(id);
    }
}