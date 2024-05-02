package com.example.springbootjava.services;

import com.example.springbootjava.services.dtos.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductService {
    ProductDto save(ProductDto productDto);

    ProductDto update(UUID id, ProductDto productDto);

    Page<ProductDto> findAll(Pageable pageable);

    ProductDto findById(UUID id);

    void delete(UUID id);
}