package com.example.clothingretailer;

// Thông tin chi tiết sản phẩm
public class Item {
    private String id; // PRIMARY KEY
    private String name;
    private int gender;
    private String category;
    private String description_path;
    private String image_path;
    private double price;
    private double rate;
    private int rate_count;

    public Item(String id, String name, int gender, String category, String description_path,
                String image_path, double price, double rate, int rate_count) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.category = category;
        this.description_path = description_path;
        this.image_path = image_path;
        this.price = price;
        this.rate = rate;
        this.rate_count = rate_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getType() {
        return category;
    }

    public void setType(String category) {
        this.category = category;
    }

    public String getDescription_path() {
        return description_path;
    }

    public void setDescription_path(String description_path) {
        this.description_path = description_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRate_count() {
        return rate_count;
    }

    public void setRate_count(int rate_count) {
        this.rate_count = rate_count;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", category='" + category + '\'' +
                ", description_path='" + description_path + '\'' +
                ", image_path='" + image_path + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", rate_count=" + rate_count +
                '}';
    }
}
