package com.example.onlinestore.service.impl;

import com.example.onlinestore.dao.ProductDao;
import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Role;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.util.NoAccessRightException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductDao productDao;

    @Override
    public ProductDto addProduct(CreateProductDto dto, User user) {
        if (user.getRole().equals(Role.USER.name())) {
            throw new NoAccessRightException();
        }

        Product product = productMapper.mapToEntity(dto);
        if (product == null) {
            throw new IllegalArgumentException("Ошибка! Получен пустой продукт");
        }

        product.setOwner(user);
        product.setRating(0);

        Instant currentDateTime = Instant.now();
        product.setDateAdded(currentDateTime);
        product.setLastUpdate(currentDateTime);

        productDao.saveProduct(product);

        return productMapper.mapToDto(product);
    }
}
