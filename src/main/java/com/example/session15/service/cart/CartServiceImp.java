package com.example.session15.service.cart;

import com.example.session15.model.Cart;
import com.example.session15.model.Order;
import com.example.session15.repsitory.cart.CartRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepositoryImp cartRepositoryImp;

    @Override
    public boolean addToCart(Cart cart) {
        return cartRepositoryImp.addCart(cart);
    }

    @Override
    public List<Cart> getAllCarts(int userId) {
        return cartRepositoryImp.getCarts(userId);
    }

    @Override
    public int getTotalCarts(int userId) {
        return cartRepositoryImp.getTotalCarts(userId);
    }

    @Override
    public boolean removeCart(int idUser) {
        return cartRepositoryImp.removeCart(idUser);
    }

    @Override
    public boolean addOrder(Order order) {
        return cartRepositoryImp.addOrder(order);
    }
}
