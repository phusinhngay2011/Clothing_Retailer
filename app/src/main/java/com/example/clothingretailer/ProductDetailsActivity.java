package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        GenerateFindViewById_ProductDetails();
    }
    private void GenerateFindViewById_ProductDetails() {
        viewPager2 = findViewById(R.id.viewPagerClothingDetails);
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

}