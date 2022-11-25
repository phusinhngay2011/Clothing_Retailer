package com.example.clothingretailer;

public class ItemAttribute {
    private String item_id;
    private String size;
    private String color;
    private int count;

    public ItemAttribute(String item_id, String size, String color, int count) {
        this.item_id = item_id;
        this.size = size;
        this.color = color;
        this.count = count;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
