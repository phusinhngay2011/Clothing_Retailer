package com.example.clothingretailer;

public class Cart {
    private String cart_id; // PRIMARY KEY
    private String username;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cart(String cart_id, String username) {
        this.cart_id = cart_id;
        this.username = username;
    }
}
