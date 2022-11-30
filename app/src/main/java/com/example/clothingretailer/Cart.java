package com.example.clothingretailer;

public class Cart {
    private int cart_id; // PRIMARY KEY
    private String username;

    public Cart(int cart_id, String username) {
        this.cart_id = cart_id;
        this.username = username;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "cart_id='" + cart_id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
