package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ImageButton profile_button = findViewById(R.id.profile_button);
        ImageButton cart_button = findViewById(R.id.cart_button);
        AppCompatButton showmore_button = findViewById(R.id.show_more_button);

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to
            }
        });

        showmore_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // load more into rv
            }
        });
    }


    public void toShoppingCart(View view) {
        Intent switchActivityIntent = new Intent(this, ShoppingCartActivity.class);
        startActivity(switchActivityIntent);
    }
}