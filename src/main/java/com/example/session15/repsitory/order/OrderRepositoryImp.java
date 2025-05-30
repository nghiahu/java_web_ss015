package com.example.session15.repsitory.order;

import com.example.session15.config.ConnectionDB;
import com.example.session15.model.Order;
import com.example.session15.model.OrderDetail;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImp implements OrderRepository {
    @Override
    public List<Order> getAllOrders(int userId) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getOrdersByUser(?)}");
            callSt.setInt(1, userId);
            rs = callSt.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderId(rs.getInt("orderId"));
                o.setIdUser(rs.getInt("idUser"));
                o.setRecipientName(rs.getString("recipientName"));
                o.setAddress(rs.getString("address"));
                o.setPhoneNumber(rs.getString("phoneNumber"));
                orders.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return orders;
    }

    @Override
    public List<OrderDetail> getOrderDetail(int orderId) {
        Connection conn = null;
        CallableStatement callSt = null;
        ResultSet rs = null;
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getOrderDetailById(?)}");
            callSt.setInt(1, orderId);
            rs = callSt.executeQuery();


            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setId(rs.getInt("id"));
                detail.setOrderId(rs.getInt("orderId"));
                detail.setProductId(rs.getInt("productId"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setCurrentPrice(rs.getDouble("currentPrice"));
                orderDetails.add(detail);
            }
            return orderDetails;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return orderDetails;
    }
}
