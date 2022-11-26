package com.example.clothingretailer;

// Những món hàng người dùng lưu (ưa thích).
public class UserPreferences {
    private String username; // PRIMARY KEY
    private String item_id; // PRIMARY KEY

    public UserPreferences(String username, String item_id) {
        this.username = username;
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "username='" + username + '\'' +
                ", item_id='" + item_id + '\'' +
                '}';
    }
}
