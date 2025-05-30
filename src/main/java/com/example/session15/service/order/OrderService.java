package com.example.session15.service.order;

import com.example.session15.model.Order;
import com.example.session15.model.OrderDetail;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders(int userId);
    List<OrderDetail> getOrderDetail(int orderId);
}
