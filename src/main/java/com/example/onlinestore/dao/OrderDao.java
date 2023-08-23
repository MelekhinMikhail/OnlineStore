package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDao {
    @Transactional(readOnly = true)
    List<Order> findAllOrders();

    @Transactional(readOnly = true)
    Order findOrderById(long id);

    @Transactional
    void saveOrder(Order order);

    @Transactional
    void updateOrder(long id, Order updatedOrder);

    @Transactional
    void deleteOrder(long id);
}
