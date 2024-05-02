package com.example.springbootjava.services.impls;

import com.example.springbootjava.controllers.ProductController;
import com.example.springbootjava.repositories.ProductRepository;
import com.example.springbootjava.services.ProductService;
import com.example.springbootjava.services.dtos.ProductDto;
import com.example.springbootjava.services.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto save(ProductDto productDto) {
        return this.productMapper.productModelToProductDto(
                this.productRepository.save(
                    this.productMapper.productDtoToProductModel(productDto)
                )
        );
    }

    @Override
    public ProductDto update(UUID id, ProductDto productDto) {
        ProductDto product = this.findById(id);
        BeanUtils.copyProperties(productDto, product, "id");
        return this.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAll(Pageable pageable) {
        Page<ProductDto> products = this.productRepository.findAll(pageable).map(this.productMapper::productModelToProductDto);
        products.forEach(p -> p.add(linkTo(methodOn(ProductController.class).getOneProduct(p.getId())).withSelfRel()));
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto findById(UUID id) {
        ProductDto product = this.productMapper.productModelToProductDto(
                this.productRepository.findById(id).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND)
                )
        );
        product.add(linkTo(methodOn(ProductController.class).getAllProducts(Pageable.ofSize(20))).withRel("Product List"));
        return product;
    }

    @Override
    public void delete(UUID id) {
        this.productRepository.deleteById(id);
    }
}