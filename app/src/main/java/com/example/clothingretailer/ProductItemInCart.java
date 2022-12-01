package com.example.clothingretailer;

public class ProductItemInCart {
    private int id;
    private String name;
    private String image_path;
    private String size;
    private String color;
    private int count;
    private int price;

    public ProductItemInCart(int id, String name, String image_path, String size, String color, int count, int price) {
        this.id = id;
        this.name = name;
        this.image_path = image_path;
        this.size = size;
        this.color = color;
        this.count = count;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
