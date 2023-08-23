package com.example.onlinestore.dao.impl;

import com.example.onlinestore.dao.OrderDao;
import com.example.onlinestore.entity.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    @Override
    public List<Order> findAllOrders() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select o from Order o", Order.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Order findOrderById(long id) {
        Session session = sessionFactory.getCurrentSession();

        return session.get(Order.class, id);
    }

    @Transactional
    @Override
    public void saveOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.save(order);
    }

    @Transactional
    @Override
    public void updateOrder(long id, Order updatedOrder) {
        Session session = sessionFactory.getCurrentSession();
        Order orderToBeUpdated = session.get(Order.class, id);

        orderToBeUpdated.setTotalCost(updatedOrder.getTotalCost());
        orderToBeUpdated.setStatus(updatedOrder.getStatus());
        orderToBeUpdated.setDateOfIssue(updatedOrder.getDateOfIssue());
        orderToBeUpdated.setUser(updatedOrder.getUser());
    }

    @Transactional
    @Override
    public void deleteOrder(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.remove(session.get(Order.class, id));
    }
}
