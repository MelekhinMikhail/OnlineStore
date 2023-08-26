package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Product;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductDao {
    List<Product> findAllProducts();

    Product findProductById(long id);

    void saveProduct(Product product);

    void updateProduct(long id, Product updatedProduct);

    void deleteProduct(long id);
}
