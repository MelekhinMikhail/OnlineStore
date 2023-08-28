package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.ProductDao;
import com.example.onlinestore.entity.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

    private final SessionFactory sessionFactory;

    @Override
    public List<Product> findAllProducts() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Product findProductById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Product.class, id);
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        session.save(product);
    }

    @Override
    public void updateProduct(long id, Product updatedProduct) {
        Session session = sessionFactory.getCurrentSession();
        Product productToBeUpdated = session.get(Product.class, id);

        productToBeUpdated.setTitle(updatedProduct.getTitle());
        productToBeUpdated.setDescription(updatedProduct.getDescription());
        productToBeUpdated.setPrice(updatedProduct.getPrice());
        productToBeUpdated.setQuantityInStock(updatedProduct.getQuantityInStock());
        productToBeUpdated.setLastUpdate(Instant.now());

    }

    @Override
    public void updateProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
    }

    @Override
    public void deleteProduct(long id) {
        Session session = sessionFactory.getCurrentSession();

        Transaction transaction = session.beginTransaction();
        session.remove(session.get(Product.class, id));
        transaction.commit();
    }
}
