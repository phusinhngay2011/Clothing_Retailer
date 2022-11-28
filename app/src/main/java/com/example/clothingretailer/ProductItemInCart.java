package com.example.clothingretailer;

public class ProductItemInCart {
    private String id;
    private String name;
    private int image_path;
    private float size;
    private String color;
    private int count;
    private int price;


    public ProductItemInCart(String id, String name, int image_path, float size, String color, int count, int price) {
        this.id = id;
        this.name = name;
        this.image_path = image_path;
        this.size = size;
        this.color = color;
        this.count = count;
        this.price = price;
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

    public int getImage_path() {
        return image_path;
    }

    public void setImage_path(int image_path) {
        this.image_path = image_path;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
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
