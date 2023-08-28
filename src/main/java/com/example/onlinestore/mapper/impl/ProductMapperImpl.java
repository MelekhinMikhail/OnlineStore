package com.example.onlinestore.mapper.impl;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.dto.product.UpdateProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapToEntity(CreateProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityInStock(dto.getQuantityInStock());

        return product;
    }

    @Override
    public ProductDto mapToDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setRating(product.getRating());
        dto.setQuantityInStock(product.getQuantityInStock());
        dto.setUserId(product.getOwner().getId());

        return dto;
    }

    @Override
    public List<ProductDto> mapToProductDtoList(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }

        List<ProductDto> dtos = new ArrayList<>();

        products.forEach(x -> dtos.add(mapToDto(x)));

        return dtos;
    }

    @Override
    public Product mapToEntity(UpdateProductDto dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityInStock(dto.getQuantityInStock());

        return product;
    }
}
