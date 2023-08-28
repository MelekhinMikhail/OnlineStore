package com.example.onlinestore.service;

import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.dto.product.UpdateProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.User;

import java.util.List;

public interface ProductService {
    ProductDto addProduct(CreateProductDto dto, User user);

    Product getProductById(long id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(UpdateProductDto dto, String email);

    void deleteProduct(long productId, String userEmail);
}
