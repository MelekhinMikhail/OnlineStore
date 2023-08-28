package com.example.onlinestore.mapper;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.dto.product.UpdateProductDto;
import com.example.onlinestore.entity.Product;

import java.util.List;

public interface ProductMapper {
    Product mapToEntity(CreateProductDto dto);

    ProductDto mapToDto(Product product);

    List<ProductDto> mapToProductDtoList(List<Product> products);

    Product mapToEntity(UpdateProductDto dto);
}
