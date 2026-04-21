package com.dev.ecommerce.service;

import com.dev.ecommerce.dto.ProductRequestDTO;
import com.dev.ecommerce.dto.ProductResponseDTO;
import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.exception.ResourceNotFoundException;
import com.dev.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = mapToEntity(productRequestDTO);
        Product saved =  productRepository.save(product);
        return mapToResponse(saved);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    private Product mapToEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(productRequestDTO.getCategory());
        return product;
    }

    private ProductResponseDTO mapToResponse(Product product) {
        return new ProductResponseDTO(product.getId(),
                                      product.getName(),
                                      product.getPrice(),
                                      product.getCategory().name());
    }
}
