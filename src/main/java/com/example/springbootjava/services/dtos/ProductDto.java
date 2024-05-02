package com.example.springbootjava.services.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto extends RepresentationModel<ProductDto> implements Serializable {
    static final long serialVersionUID = 1L;

    @Id
    UUID id;

    @NotBlank
    String name;

    @NotNull
    BigDecimal value;
}