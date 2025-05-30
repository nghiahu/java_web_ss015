package com.example.session15.repsitory.cart;

import com.example.session15.model.Cart;
import com.example.session15.model.Order;

import java.util.List;

public interface CartRepository {
    List<Cart> getCarts(int idUser);
    int getTotalCarts(int idUser);
    boolean addCart(Cart cart);
    boolean removeCart(int idUser);
    boolean addOrder(Order order);
}
