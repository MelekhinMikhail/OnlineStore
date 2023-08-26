package com.example.onlinestore.dao;

import com.example.onlinestore.entity.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderItemDao {

    List<OrderItem> findAllOrderItems();

    OrderItem findOrderItemById(long id);

    void saveOrderItem(OrderItem orderItem);

    void updateOrderItem(long id, OrderItem updatedOrderItem);

    void deleteOrderItem(long id);
}
