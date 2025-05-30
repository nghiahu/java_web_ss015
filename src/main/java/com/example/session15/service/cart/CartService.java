package com.example.session15.service.cart;

import com.example.session15.model.Cart;
import com.example.session15.model.Order;

import java.util.List;

public interface CartService {
    boolean addToCart(Cart cart);
    List<Cart> getAllCarts(int userId);
    int getTotalCarts(int userId);
    boolean removeCart(int idUser);
    boolean addOrder(Order order);
}
