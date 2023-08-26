package com.example.onlinestore.service;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.entity.User;

public interface ProductService {
    ProductDto addProduct(CreateProductDto dto, User user);
}
