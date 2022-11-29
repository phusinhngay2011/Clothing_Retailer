package com.example.clothingretailer;

public class UserLike {
    private String username;
    private int item_id;

    public UserLike(String username, int item_id) {
        this.username = username;
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String toString()
    {
        String res = "UserLike{" +
                "username='" + username + '\'' +
                ", item_id='" + item_id + "\'}";

        return res;
    }
}
