package com.example.clothingretailer;

public class CartItem {
    private int cart_id; // PRIMARY KEY
    private int item_id; // PRIMARY KEY
    private int item_count;

    public CartItem(int cart_id, int item_id, int item_count) {
        this.cart_id = cart_id;
        this.item_id = item_id;
        this.item_count = item_count;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id='" + cart_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", item_count=" + item_count +
                '}';
    }

}
