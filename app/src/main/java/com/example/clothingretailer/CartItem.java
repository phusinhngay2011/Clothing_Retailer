package com.example.clothingretailer;

public class CartItem {
    private int cart_id; // PRIMARY KEY
    private int item_id; // PRIMARY KEY
    private String size;
    private String color;
    private int item_count;

    public CartItem(int cart_id, int item_id, String size, String color, int item_count) {
        this.cart_id = cart_id;
        this.item_id = item_id;
        this.size = size;
        this.color = color;
        this.item_count = item_count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id='" + cart_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", item_count=" + item_count +
                '}';
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }
}
