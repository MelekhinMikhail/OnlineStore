package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.entity.Product;

public interface ProductMapper {
    Product mapToEntity(CreateProductDto dto);

    ProductDto mapToDto(Product product);
}
