package com.example.session15.repsitory.cart;


import com.example.session15.config.ConnectionDB;
import com.example.session15.model.Cart;
import com.example.session15.model.Order;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImp implements CartRepository {
    @Override
    public List<Cart> getCarts(int idUser) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Cart> carts = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call getCartByUser(?)}");
            callSt.setInt(1, idUser);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setIdCart(rs.getInt("idCart"));
                cart.setIdUser(rs.getInt("idUser"));
                cart.setIdProduct(rs.getInt("idProduct"));
                cart.setQuantity(rs.getInt("quantity"));
                carts.add(cart);
            }
            return carts;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return carts;
    }

    @Override
    public int getTotalCarts(int idUser) {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalCarts = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("call getTotalCartByUser(?)");
            callSt.setInt(1, idUser);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                totalCarts = rs.getInt("total_amount");
                return totalCarts;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalCarts;
    }

    @Override
    public boolean addCart(Cart cart) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addToCart(?,?)}");
            callSt.setInt(1, cart.getIdUser());
            callSt.setInt(2, cart.getIdProduct());
            callSt.execute();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean removeCart(int idUser) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call clearCart(?)}");
            callSt.setInt(1, idUser);
            callSt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean addOrder(Order order) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addOrder(?,?,?,?)}");
            callSt.setInt(1, order.getIdUser());
            callSt.setString(2, order.getRecipientName());
            callSt.setString(3, order.getAddress());
            callSt.setString(4, order.getPhoneNumber());
            callSt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

}
