package com.example.clothingretailer;

// Thông tin chi tiết sản phẩm
public class Item {
    private int id; // PRIMARY KEY
    private String name;
    private int gender;
    private String type;
    private String description_title;
    private String description;
    private String highlight_title;
    private String highlight;
    private String image_path;
    private int price;
    public static final int MALE = 1;
    public static final int FEMALE = 2;
    public static final int BOTH_GENDERS = 0;


    public Item(int id, String name, int gender, String type, String description_title, String description, String highlight_title, String highlight, String image_path, int price) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.type = type;
        this.description_title = description_title;
        this.description = description;
        this.highlight_title = highlight_title;
        this.highlight = highlight;
        this.image_path = image_path;
        this.price = price;
    }




    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", type='" + type + '\'' +
                ", description_title='" + description_title + '\'' +
                ", description='" + description + '\'' +
                ", highlight_title='" + highlight_title + '\'' +
                ", highlight='" + highlight + '\'' +
                ", image_path='" + image_path + '\'' +
                ", price=" + price +
                '}';
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription_title() {
        return description_title;
    }

    public void setDescription_title(String description_title) {
        this.description_title = description_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHighlight_title() {
        return highlight_title;
    }

    public void setHighlight_title(String highlight_title) {
        this.highlight_title = highlight_title;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
