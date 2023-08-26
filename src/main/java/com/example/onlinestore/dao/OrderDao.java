package com.example.onlinestore.dao;

import com.example.onlinestore.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDao {

    List<Order> findAllOrders();

    Order findOrderById(long id);

    void saveOrder(Order order);

    void updateOrder(long id, Order updatedOrder);

    void deleteOrder(long id);
}
