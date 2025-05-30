package com.example.session15.repsitory.order;

import com.example.session15.model.Order;
import com.example.session15.model.OrderDetail;

import java.util.List;

public interface OrderRepository {
    List<Order> getAllOrders(int userId);
    List<OrderDetail> getOrderDetail(int orderId);
}
