package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.ui.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    RadioButton sizeSBtn, sizeMBtn, sizeLBtn, sizeXLBtn, sizeXXLBtn, size3XLBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        GenerateFindViewById_ProductDetails();
        viewPager2Handler();


    }

    private void GenerateFindViewById_ProductDetails() {
        viewPager2 = findViewById(R.id.viewPagerClothingDetails);
        sizeSBtn = (RadioButton) findViewById(R.id.btnSizeS);
        sizeMBtn = (RadioButton) findViewById(R.id.btnSizeM);
        sizeLBtn = (RadioButton) findViewById(R.id.btnSizeL);
        sizeXLBtn = (RadioButton) findViewById(R.id.btnSizeXL);
        sizeXXLBtn = (RadioButton) findViewById(R.id.btnSizeXXL);
        size3XLBtn = (RadioButton) findViewById(R.id.btnSize3XL);
    }

    private void viewPager2Handler() {
        List<SliderItem> sliderItems = createSliderItem();
        setAdapterHanlder(sliderItems);
    }

    private void setAdapterHanlder(List<SliderItem> sliderItems) {
        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        // Cho xem trước 1 phần ảnh trước và ảnh sau
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        // Set transformer cho ViewPager
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer(){

            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);

            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
    }



    private List<SliderItem> createSliderItem() {
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_1));
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_2));
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_3));
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_4));
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_5));
        sliderItems.add(new SliderItem(R.drawable.clothing_ex_details_6));
        return sliderItems;
    }


    public void onSizeProduct(View view){
        int selectedId = view.getId();
        switch (selectedId){
            case R.id.btnSizeS:
                updateSizeProductRadioBtn(sizeSBtn);
                break;
            case R.id.btnSizeM:
                updateSizeProductRadioBtn(sizeMBtn);
                break;
            case R.id.btnSizeL:
                updateSizeProductRadioBtn(sizeLBtn);
                break;
            case R.id.btnSizeXL:
                updateSizeProductRadioBtn(sizeXLBtn);
                break;
            case R.id.btnSizeXXL:
                updateSizeProductRadioBtn(sizeXXLBtn);
                break;
            case R.id.btnSize3XL:
                updateSizeProductRadioBtn(size3XLBtn);
                break;
        }
    }

    private void updateSizeProductRadioBtn(RadioButton selected){
        sizeSBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        sizeSBtn.setTextColor(getResources().getColor(R.color.black));

        sizeMBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        sizeMBtn.setTextColor(getResources().getColor(R.color.black));

        sizeLBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        sizeLBtn.setTextColor(getResources().getColor(R.color.black));

        sizeXLBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        sizeXLBtn.setTextColor(getResources().getColor(R.color.black));

        sizeXXLBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        sizeXXLBtn.setTextColor(getResources().getColor(R.color.black));

        size3XLBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_off));
        size3XLBtn.setTextColor(getResources().getColor(R.color.black));

        selected.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.rounded_btn_size_on));
        selected.setTextColor(getResources().getColor(R.color.white));
    }

    public void onClickAddtoCart(View view) {

    }

    public void onClickBuyNow(View view) {

    }

    public void onClickShareProduct(View view) {

    }

    public void onClickLikeProduct(View view) {

    }
}