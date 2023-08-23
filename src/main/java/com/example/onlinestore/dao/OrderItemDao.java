package com.example.onlinestore.dao;

import com.example.onlinestore.entity.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemDao {
    @Transactional(readOnly = true)
    List<OrderItem> findAllOrderItems();

    @Transactional(readOnly = true)
    OrderItem findOrderItemById(long id);

    @Transactional
    void saveOrderItem(OrderItem orderItem);

    @Transactional
    void updateOrderItem(long id, OrderItem updatedOrderItem);

    @Transactional
    void deleteOrderItem(long id);
}
