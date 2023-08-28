package com.example.onlinestore.service.impl;

import com.example.onlinestore.dao.ProductDao;
import com.example.onlinestore.dto.product.CreateProductDto;
import com.example.onlinestore.dto.product.ProductDto;
import com.example.onlinestore.dto.product.UpdateProductDto;
import com.example.onlinestore.entity.Product;
import com.example.onlinestore.entity.Role;
import com.example.onlinestore.entity.User;
import com.example.onlinestore.mapper.ProductMapper;
import com.example.onlinestore.service.ProductService;
import com.example.onlinestore.service.UserService;
import com.example.onlinestore.util.NoAccessRightException;
import com.example.onlinestore.util.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductDao productDao;
    private final UserService userService;

    @Override
    public ProductDto addProduct(CreateProductDto dto, User user) {
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

    @Override
    public Product getProductById(long id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDtoList(productDao.findAllProducts());
    }

    @Override
    public ProductDto updateProduct(UpdateProductDto dto, String email) {
        if (dto == null) {
            return null;
        }

        Product product = getProductById(dto.getId());
        if (product == null) {
            throw new ProductNotFoundException("Продукт с id " + dto.getId() + " не найден");
        }

        User user = userService.findUserByEmail(email);
        if (user.getId() != dto.getUserId() && !user.getRole().equals(Role.ROLE_ADMIN.name())) {
            throw new NoAccessRightException("Нет доступа к продукту с id " + dto.getId());
        }

        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setQuantityInStock(dto.getQuantityInStock());
        product.setLastUpdate(Instant.now());

        productDao.updateProduct(product);

        return productMapper.mapToDto(product);
    }

    @Override
    public void deleteProduct(long productId, String userEmail) {
        Product product = getProductById(productId);
        if (product == null) {
            throw new ProductNotFoundException("Продукт с id " + productId + " не найден");
        }

        User user = userService.findUserByEmail(userEmail);
        if (user.getId() != product.getOwner().getId() && !user.getRole().equals(Role.ROLE_ADMIN.name())) {
            throw new NoAccessRightException("Нет доступа к продукту с id " + productId);
        }

        productDao.deleteProduct(productId);
    }
}
