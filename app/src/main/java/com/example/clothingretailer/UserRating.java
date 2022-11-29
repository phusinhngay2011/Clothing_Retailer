package com.example.clothingretailer;

// Đánh giá sản phẩm
public class UserRating {
    private int item_id; // PRIMARY KEY
    private String username; // PRIMARY KEY
    private float rating_score;

    public UserRating(int item_id, String username, float rating_score) {
        this.item_id = item_id;
        this.username = username;
        this.rating_score = rating_score;
    }


    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating_score() {
        return rating_score;
    }

    public void setRating_score(float rating_score) {
        this.rating_score = rating_score;
    }

    @Override
    public String toString() {
        return "UserRating{" +
                "item_id='" + item_id + '\'' +
                ", username='" + username + '\'' +
                ", rating_score=" + rating_score +
                '}';
    }

}
