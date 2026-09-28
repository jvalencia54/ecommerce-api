package com.ecommerce.demo.products.service;

import com.ecommerce.demo.products.dto.ProductRequestDto;
import com.ecommerce.demo.products.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(Long id);
    ProductResponseDto createProduct(ProductRequestDto requestDTO);
    ProductResponseDto updateProduct(Long id, ProductRequestDto requestDTO);
    void deleteProduct(Long id);
}
