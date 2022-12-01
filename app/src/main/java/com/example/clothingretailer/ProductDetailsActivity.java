package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.compose.ui.graphics.Color;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    RadioButton sizeSBtn, sizeMBtn, sizeLBtn, sizeXLBtn, sizeXXLBtn, size3XLBtn;
    RadioButton blackBtn, whiteBtn, greyBtn, beBtn;
    CheckBox[] rating = new CheckBox[5];
    TextView ratingTV;
    private Item item;
    private TextView tv_item_name, tv_price;
    private AppCompatButton add2cart_button;
    private AppCompatButton buy_now_button;
    private RadioGroup rg_size;
    private RadioGroup rg_color;
    private TextView tv_highlight_title, tv_highlight, tv_des_title, tv_des;
    int onRating = 1;

    private FrameLayout ratingFL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        this.item = GlobalVars.selected_item;
        GenerateFindViewById_ProductDetails();
        viewPager2Handler();
        if (this.item != null)
        {
            tv_item_name.setText(item.getName());
            tv_price.setText(ShoppingCartActivity.formatPriceString(item.getPrice()));
            tv_des_title.setText(item.getDescription_title());
            tv_des.setText(item.getDescription());
            tv_highlight_title.setText(item.getHighlight_title());
            tv_highlight.setText(item.getHighlight());
        }

    }

    private void GenerateFindViewById_ProductDetails() {
        viewPager2 = findViewById(R.id.viewPagerClothingDetails);
        sizeSBtn = (RadioButton) findViewById(R.id.btnSizeS);
        sizeMBtn = (RadioButton) findViewById(R.id.btnSizeM);
        sizeLBtn = (RadioButton) findViewById(R.id.btnSizeL);
        sizeXLBtn = (RadioButton) findViewById(R.id.btnSizeXL);
        sizeXXLBtn = (RadioButton) findViewById(R.id.btnSizeXXL);
        size3XLBtn = (RadioButton) findViewById(R.id.btnSize3XL);
        blackBtn = (RadioButton) findViewById(R.id.colorBlackRB);
        whiteBtn = (RadioButton) findViewById(R.id.colorWhiteRB);
        greyBtn = (RadioButton) findViewById(R.id.colorGreyRB);
        beBtn = (RadioButton) findViewById(R.id.colorBeRB);

        ratingFL = (FrameLayout) findViewById(R.id.frameRating);
        rating[0] =  findViewById(R.id.rate1);
        rating[1] =  findViewById(R.id.rate2);
        rating[2] =  findViewById(R.id.rate3);
        rating[3] =  findViewById(R.id.rate4);
        rating[4] =  findViewById(R.id.rate5);
        ratingTV = findViewById(R.id.displayRatingTV);

        tv_item_name = findViewById(R.id.tv_item_name);
        tv_price = findViewById(R.id.tv_price);
        add2cart_button = findViewById(R.id.addToCart);
        buy_now_button = findViewById(R.id.BuyNow);
        rg_size = findViewById(R.id.sizeRadioGroup);
        rg_color = findViewById(R.id.colorRadioGroup);
        tv_des_title = findViewById(R.id.desProduct1);
        tv_des = findViewById(R.id.desProduct1_1);
        tv_highlight_title = findViewById(R.id.HighlightProduct);
        tv_highlight = findViewById(R.id.Highlight);
    }

    private void viewPager2Handler() {
        if (item.getImage_path() != null && item.getImage_path().length() > 0)
        {
            List<SliderItem> sliderItems = createSliderItem(item.getImage_path());
            setAdapterHanlder(sliderItems);
        }
    }

    private void setAdapterHanlder(List<SliderItem> sliderItems) {
        viewPager2.setAdapter(new SliderAdapter(ProductDetailsActivity.this, sliderItems, viewPager2));

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

    private void showOrHideTV(TextView tv){
        if(tv.getVisibility() == View.VISIBLE){
            tv.setVisibility(View.GONE);
        }
        else{
            tv.setVisibility(View.VISIBLE);
        }
    }
    private void showOrHideFrameLayout(FrameLayout frameLayout) {
        if (frameLayout.getVisibility() == View.VISIBLE){
            TranslateAnimation animate = new TranslateAnimation(
                    rating[4].getWidth(),                 // fromXDelta
                    frameLayout.getWidth() + rating[4].getWidth() + 60,// toXDelta
                    0,  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            frameLayout.startAnimation(animate);
            frameLayout.setVisibility(View.GONE);
        }
        else{
            TranslateAnimation animate = new TranslateAnimation(
                    frameLayout.getWidth() + rating[4].getWidth(),   // fromXDelta
                    0, // toXDelta
                    0,  // fromYDelta
                    0);                // toYDelta
            animate.setDuration(500);
            animate.setFillAfter(true);
            frameLayout.startAnimation(animate);
            frameLayout.setVisibility(View.VISIBLE);
        }
    }

    public void onRating(View view) {
        onRating = 1 - onRating;
        showOrHideFrameLayout(ratingFL);
        showOrHideTV(ratingTV);
        if(onRating == 1)
            updateRating(view.getId());
        else
            rating[4].setButtonDrawable(R.drawable.ic_baseline_star_border_50);
    }

    private void updateRating(int id) {
        int cnt = 0, i = 0;
        switch (id) {
            case R.id.rate1:
                cnt = 1;
                break;
            case R.id.rate2:
                cnt = 2;
                break;
            case R.id.rate3:
                cnt = 3;
                break;
            case R.id.rate4:
                cnt = 4;
                break;
            case R.id.rate5:
                cnt = 5;
                break;
        }
        for(; i < cnt; i++){
            rating[i].setButtonDrawable(R.drawable.ic_baseline_star_rate_50);
        }
        for(i = cnt; i < rating.length - 1; i++){
            rating[i].setButtonDrawable(R.drawable.ic_baseline_star_border_50);
        }

        if(cnt < rating.length)
            rating[4].setButtonDrawable(R.drawable.ic_baseline_star_half_50);

        ratingTV.setText(new String(String.valueOf(cnt)));
        ratingTV.setTextSize(20);

    }

    public void displayRating(View view) {

    }
    private List<SliderItem> createSliderItem(String url_imgs_str) {
        List<String> url_imgs = StringHdr.getURLImgs(url_imgs_str);
        List<SliderItem> sliderItems = new ArrayList<>();
        for(int i = 0;i < url_imgs.size();i++)
            sliderItems.add(new SliderItem(url_imgs.get(i)));
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

    public void onColorProduct(View view){
        int selectedId = view.getId();
        RadioButton selected = findViewById(selectedId);
        updateColorProductRadioBtn(selected);
    }

    private void updateColorProductRadioBtn(RadioButton selected){
        blackBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.radio_btn_black_off));
        whiteBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.radio_btn_white_off));
        greyBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.radio_btn_grey_off));
        beBtn.setBackground(ContextCompat.getDrawable(
                getApplicationContext(), R.drawable.radio_btn_be_off));

        switch (selected.getId()){
            case R.id.colorBlackRB:
                selected.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.radio_btn_black_on));
                break;

            case R.id.colorWhiteRB:
                selected.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.radio_btn_white_on));
                break;
            case R.id.colorGreyRB:
                selected.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.radio_btn_grey_on));
                break;
            case R.id.colorBeRB:
                selected.setBackground(ContextCompat.getDrawable(
                        getApplicationContext(), R.drawable.radio_btn_be_on));
                break;
        }
    }
    public void onClickAddtoCart(View view) {
        String color = getColor();
        String size = getSize();
        boolean found = false;
        for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
            if (item == GlobalVars.current_cart_items.get(i) && size == GlobalVars.current_cart_sizes.get(i) && color == GlobalVars.current_cart_colors.get(i))
            {
                found = true;
                Integer tmp = GlobalVars.current_cart_item_counts.get(i) + 1;
                GlobalVars.current_cart_item_counts.set(i, tmp);
                break;
            }
        if (found == false)
        {
            GlobalVars.current_cart_items.add(item);
            GlobalVars.current_cart_sizes.add(size);
            GlobalVars.current_cart_colors.add(color);
            GlobalVars.current_cart_item_counts.add(1);
        }
    }

    public void onClickBuyNow(View view) {

    }

    public void onClickShareProduct(View view) {

    }

    public void onClickLikeProduct(View view) {

    }
    public void toHome(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public String getColor()
    {
        int id = rg_color.getCheckedRadioButtonId();
        Log.d("tag", String.valueOf(id));
        if (id == blackBtn.getId())
            return "#000000";
        else if (id == whiteBtn.getId())
            return "#FFFFFF";
        else if (id == greyBtn.getId())
            return "#A9A9A9";
        else if (id == beBtn.getId())
            return "#F5F5DC";

        return null;
    }

    public String getSize()
    {
        int id = rg_size.getCheckedRadioButtonId();
        Log.d("tag", String.valueOf(id));
        if (id == sizeSBtn.getId())
            return "S";
        else if (id == sizeMBtn.getId())
            return "M";
        else if (id == sizeLBtn.getId())
            return  "L";
        else if (id == sizeXLBtn.getId())
            return "XL";
        else if (id == sizeXXLBtn.getId())
            return "XXL";
        else if (id == size3XLBtn.getId())
            return "3XL";

        return null;
    }
}