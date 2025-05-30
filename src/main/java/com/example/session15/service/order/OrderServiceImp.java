package com.example.session15.service.order;

import com.example.session15.model.Order;
import com.example.session15.model.OrderDetail;
import com.example.session15.repsitory.order.OrderRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepositoryImp orderRepositoryImp;

    @Override
    public List<Order> getAllOrders(int userId) {
        return orderRepositoryImp.getAllOrders(userId);
    }

    @Override
    public List<OrderDetail> getOrderDetail(int orderId) {
        return orderRepositoryImp.getOrderDetail(orderId);
    }
}
