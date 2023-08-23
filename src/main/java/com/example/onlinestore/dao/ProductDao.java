package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao {
    @Transactional(readOnly = true)
    List<Product> findAllProducts();

    @Transactional(readOnly = true)
    Product findProductById(long id);

    @Transactional
    void saveProduct(Product product);

    @Transactional
    void updateProduct(long id, Product updatedProduct);

    @Transactional
    void deleteProduct(long id);
}
