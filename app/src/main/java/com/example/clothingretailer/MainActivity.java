package com.example.clothingretailer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final int validAge = 13;

    // For Slider
    private ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.sign_in_main);
        setContentView(R.layout.product_details);

        GenerateFindViewById();
        viewPager2Handler();
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

    private void GenerateFindViewById() {
        viewPager2 = findViewById(R.id.viewPagerClothingDetails);
    }



    public void toSignUp(View view){
        setContentView(R.layout.sign_up_main);
    }

    public void toSignUpGeneralInf(View view){
        setContentView(R.layout.sign_up_general_inf);
    }

    public void toSignUpFinalStep(View view){
        setContentView(R.layout.sign_up_final_step);
    }

    public void  toSignUpComplessness(View view){
        setContentView(R.layout.sign_up_completeness);
    }

    public void toSignIn(View view){
        setContentView(R.layout.sign_in_main);
    }

    public void popUpDatePicker(View view){
        EditText dateEdt = findViewById(R.id.BirthdaySignUpEditText);
        final Calendar c = Calendar.getInstance();

        int _year = c.get(Calendar.YEAR);
        int _month = c.get(Calendar.MONTH);
        int _day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        int diff = _year - year;
                        if(monthOfYear > _month ||
                                (monthOfYear == _month && dayOfMonth > _day))
                            diff--;
                        if(diff < validAge){
                            Context context = getApplicationContext();
                            CharSequence text = "Invalid Age!";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        else
                            dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }},
                _year - 13, _month, _day);
            datePickerDialog.show();
    }

    public void onGenderGroup(View view) {
        RadioGroup genderRadioGroup = (RadioGroup)view;
        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               group.clearCheck();
               group.check(checkedId);
            }
        });
    }
}

