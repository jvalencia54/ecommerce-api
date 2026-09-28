package com.ecommerce.demo.products.dto;

import com.ecommerce.demo.products.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toDTO(Product entity) {
        if (entity == null) return null;
        return new ProductResponseDto(
                entity.getIdProduct(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getStock(),
                entity.getImageUrl()
        );
    }

    public Product toEntity(ProductRequestDto dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        return product;
    }
}
