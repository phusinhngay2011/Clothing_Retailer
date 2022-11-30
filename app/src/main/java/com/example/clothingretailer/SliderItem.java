package com.example.clothingretailer;

public class SliderItem {
    private int img;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SliderItem(String url) {
        this.url = url;
    }

    SliderItem(int img){
        this.img = img;
    }
    public int getImg(){
        return img;
    }
}
