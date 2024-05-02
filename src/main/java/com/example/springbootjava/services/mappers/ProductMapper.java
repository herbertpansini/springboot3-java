package com.example.springbootjava.services.mappers;

import com.example.springbootjava.models.ProductModel;
import com.example.springbootjava.services.dtos.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productModelToProductDto(ProductModel productModel);

    @InheritInverseConfiguration
    ProductModel productDtoToProductModel(ProductDto productDto);
}
