package com.example.clothingretailer;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    ImageButton home_button;
    ImageButton cart_button;
    Spinner gender_selector;
    Spinner type_selector;
    private String current_gender = null;
    private String current_type = null;
    private ArrayList<Item> items = null;
    private DBHandler dbHandler = null;
    private boolean init1 = false, init2 = false;
    RecyclerView main_rv;

    public SearchActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        generateViewById();
        setButtonOnclick();
        setupSpinner();
        //Log.d("tag", "search onCreate called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dbHandler != null)
        {
            dbHandler.close_DB();
            dbHandler = null;
        }
        if (items != null && items.size() > 0)
            items.clear();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (dbHandler == null)
            dbHandler = new DBHandler(getApplicationContext());
    }


    private void generateViewById()
    {
        home_button = findViewById(R.id.home_button);
        cart_button = findViewById(R.id.cart_button);
        gender_selector = findViewById(R.id.gender_selector);
        type_selector = findViewById(R.id.clothes_type_selector);
        main_rv = findViewById(R.id.current_list);
    }

    private void setButtonOnclick()
    {
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCartIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toCartIntent);
            }
        });
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCartIntent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
                startActivity(toCartIntent);
            }
        });
    }

    private void setupSpinner()
    {
        gender_selector.setOnItemSelectedListener(new GenderSpinnerClass());
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, (String[]) getResources().getStringArray(R.array.genders));
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender_selector.setAdapter(ad);
        gender_selector.setPadding(5, 5, 5, 5);

        type_selector.setOnItemSelectedListener(new TypeSpinnerClass());
        ArrayAdapter ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, (String[]) getResources().getStringArray(R.array.clothes_types));
        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_selector.setAdapter(ad2);
        gender_selector.setPadding(5, 5, 5, 5);
    }

    private void setupRV() {
        //Log.d("tag", current_gender + current_type);
        if (items != null) {
            items.clear();
        }
        int gender;
        if (current_gender.equals("Men"))
            gender = Item.MALE;
        else if (current_gender.equals("Women"))
            gender = Item.FEMALE;
        else
            gender = Item.BOTH_GENDERS;

        items = dbHandler.search_item(null, gender, (current_type == null || current_type.equals("All types") ? null : current_type));

        if (items != null) {
            //Log.d("tag", String.valueOf(items.size()));
            main_rv.setAdapter(new SeachRVAdapter(SearchActivity.this, this.items));
            main_rv.setLayoutManager(new GridLayoutManager(SearchActivity.this, 2));

        }
    }

    public void toShoppingCart(View view) {
        Intent switchActivityIntent = new Intent(this, ShoppingCartActivity.class);
        startActivity(switchActivityIntent);
    }

    class GenderSpinnerClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            current_gender = getResources().getStringArray(R.array.genders)[i];
            if (init1 == false)
            {
                init1 = true;
                if (init2 == true)
                    setupRV();
            }
            else if (init2 == true)
                    setupRV();

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    class TypeSpinnerClass implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            current_type = getResources().getStringArray(R.array.clothes_types)[i];
            if (init2 == false)
            {
                init2 = true;
                if (init1 == true)
                    setupRV();
            }
            else if (init1 == true)
                setupRV();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

}