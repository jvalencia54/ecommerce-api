package com.ecommerce.demo.products.service;

import com.ecommerce.demo.products.dto.ProductMapper;
import com.ecommerce.demo.products.dto.ProductRequestDto;
import com.ecommerce.demo.products.dto.ProductResponseDto;
import com.ecommerce.demo.products.entity.Product;
import com.ecommerce.demo.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductsRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductsRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        return productMapper.toDTO(product);
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto requestDTO) {
        Product product = productMapper.toEntity(requestDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(Long id, ProductRequestDto requestDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));

        existingProduct.setName(requestDTO.getName());
        existingProduct.setDescription(requestDTO.getDescription());
        existingProduct.setPrice(requestDTO.getPrice());
        existingProduct.setStock(requestDTO.getStock());
        if (requestDTO.getImageUrl() != null) {
            existingProduct.setImageUrl(requestDTO.getImageUrl());
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
        productRepository.delete(existingProduct);
    }
}
