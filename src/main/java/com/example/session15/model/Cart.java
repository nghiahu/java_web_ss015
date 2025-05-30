package com.example.session15.model;

public class Cart {
    private int idCart;
    private int idUser;
    private int idProduct;
    private int quantity;

    public Cart() {
    }

    public Cart(int idCart, int idUser, int idProduct, int quantity) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
