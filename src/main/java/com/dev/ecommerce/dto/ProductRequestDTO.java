package com.dev.ecommerce.dto;

import com.dev.ecommerce.enums.ProductCategory;
import org.jspecify.annotations.NonNull;

import java.math.BigDecimal;

public class ProductRequestDTO {

    private Long id;
    private String name;
    @Deci
    private BigDecimal price;
    private ProductCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
