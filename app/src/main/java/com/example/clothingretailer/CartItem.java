package com.example.clothingretailer;

public class CartItem {
    private String cart_id; // PRIMARY KEY
    private String item_id; // PRIMARY KEY
    private int count_item;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getCount_item() {
        return count_item;
    }

    public void setCount_item(int count_item) {
        this.count_item = count_item;
    }

    public CartItem(String cart_id, String item_id, int count_item) {
        this.cart_id = cart_id;
        this.item_id = item_id;
        this.count_item = count_item;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cart_id='" + cart_id + '\'' +
                ", item_id='" + item_id + '\'' +
                ", count_item=" + count_item +
                '}';
    }
}
