package com.example.session15.controller;

import com.example.session15.model.Order;
import com.example.session15.model.OrderDetail;
import com.example.session15.service.order.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;

    @GetMapping("listOrder")
    public String listOrder(Model model) {
        model.addAttribute("orders",orderServiceImp.getAllOrders(1));
        return "listOrder";
    }

    @GetMapping("/orderDetail")
    public String orderDetail(@RequestParam("orderId") int orderId, Model model) {
        List<OrderDetail> orderDetails = orderServiceImp.getOrderDetail(orderId);
        model.addAttribute("orderDetails", orderDetails);
        return "orderDetail";
    }

}
