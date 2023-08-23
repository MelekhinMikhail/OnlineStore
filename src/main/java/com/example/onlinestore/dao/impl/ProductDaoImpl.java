package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.ProductDao;
import com.example.onlinestore.entity.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Product findProductById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Product.class, id);
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        session.save(product);
    }

    @Transactional
    @Override
    public void updateProduct(long id, Product updatedProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product productToBeUpdated = session.get(Product.class, id);

        productToBeUpdated.setTitle(updatedProduct.getTitle());
        productToBeUpdated.setDescription(updatedProduct.getDescription());
        productToBeUpdated.setPrice(updatedProduct.getPrice());
        productToBeUpdated.setImagePath(updatedProduct.getImagePath());
        productToBeUpdated.setQuantityInStock(updatedProduct.getQuantityInStock());
        productToBeUpdated.setRating(updatedProduct.getRating());
        productToBeUpdated.setDateAdded(updatedProduct.getDateAdded());
        productToBeUpdated.setLastUpdate(updatedProduct.getLastUpdate());
        productToBeUpdated.setOwner(updatedProduct.getOwner());
    }

    @Transactional
    @Override
    public void deleteProduct(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Product.class, id));
    }
}
