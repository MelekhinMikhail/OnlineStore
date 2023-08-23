package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.OrderItemDao;
import com.example.onlinestore.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderItemDaoImpl implements OrderItemDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<OrderItem> findAllOrderItems() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select o from OrderItem o", OrderItem.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public OrderItem findOrderItemById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(OrderItem.class, id);
    }

    @Transactional
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        Session session = sessionFactory.getCurrentSession();

        session.save(orderItem);
    }

    @Transactional
    @Override
    public void updateOrderItem(long id, OrderItem updatedOrderItem) {
        Session session = sessionFactory.getCurrentSession();
        OrderItem orderItemToBeUpdated = session.get(OrderItem.class, id);

        orderItemToBeUpdated.setQuantity(updatedOrderItem.getQuantity());
        orderItemToBeUpdated.setProduct(updatedOrderItem.getProduct());
        orderItemToBeUpdated.setOrder(updatedOrderItem.getOrder());
    }

    @Transactional
    @Override
    public void deleteOrderItem(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(OrderItem.class, id));
    }
}
