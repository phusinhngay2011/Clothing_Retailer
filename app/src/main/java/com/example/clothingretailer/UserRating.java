package com.example.clothingretailer;

// Đánh giá sản phẩm
public class UserRating {
    private String item_id; // PRIMARY KEY
    private String username; // PRIMARY KEY
    private int rate;

    public UserRating(String item_id, String username, int rate) {
        this.item_id = item_id;
        this.username = username;
        this.rate = rate;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
