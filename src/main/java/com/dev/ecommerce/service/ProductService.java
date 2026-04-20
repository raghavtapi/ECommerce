package com.dev.ecommerce.service;

import com.dev.ecommerce.dto.ProductRequestDTO;
import com.dev.ecommerce.dto.ProductResponseDTO;
import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    private Product mapToEntity(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setProductCategory(productRequestDTO.getCategory());
        return product;
    }

    private ProductResponseDTO mapToResponse(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setCategory(product.getProductCategory());
        return productResponseDTO;
    }
}
