package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ShoppingCartActivity extends AppCompatActivity {
    private static ArrayList<ProductItemInCart> mProductItems;
    private RecyclerView mRecyclerView;
    private ProductItemAdapter mProductItemAdapter;
    private static TextView mTotalPrice;
    private static TextView mShippingFee;
    private static ImageView mImageEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mRecyclerView = findViewById(R.id.recyclerView_cart);
        mTotalPrice = findViewById(R.id.total_price);
        mShippingFee = findViewById(R.id.shipping_fee);
        mProductItems = new ArrayList<ProductItemInCart>();
        mImageEmpty = findViewById(R.id.image_empty_cart);
        loadProductItems();

        mProductItemAdapter = new ProductItemAdapter(this, mProductItems);
        mRecyclerView.setAdapter(mProductItemAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        updateCart();
    }

    public static void updateCart() {
        int total = 0;
        int cnt = 0;
        for (ProductItemInCart i : mProductItems) {
            total += i.getPrice()*i.getCount();
            cnt += i.getCount();
        }
        mTotalPrice.setText(formatPriceString(total));
        if (cnt > 0 && cnt < 5) mShippingFee.setText(formatPriceString(50000));
        else mShippingFee.setText("Free");
        if (cnt == 0) mImageEmpty.setVisibility(View.VISIBLE);
        else mImageEmpty.setVisibility(View.INVISIBLE);
    }

    public static String formatPriceString(int price) {
        String res = String.valueOf(price);
        int len = res.length();
        int position = len - 3;
        for (int i = 0; i < len / 3; i++) {
            res = res.substring(0, position) + '.' + res.substring(position);
            position -= 3;
        }
        return res;
    }

    private void loadProductItems() {
        // Replace by loading data from DB
        mProductItems.add(new ProductItemInCart("001", "Adidas Stan Smith", R.drawable.ic_launcher_background, (float)35.5, "White", 1, 2500000));
        mProductItems.add(new ProductItemInCart("002", "Adidas Lite Racer", R.drawable.ic_launcher_background, (float)38.5, "Black", 2, 2000000));
        mProductItems.add(new ProductItemInCart("001", "Adidas Stan Smith", R.drawable.ic_launcher_background, (float)35.5, "White", 1, 2500000));
        mProductItems.add(new ProductItemInCart("002", "Adidas Lite Racer", R.drawable.ic_launcher_background, (float)38.5, "Black", 2, 2000000));
        mProductItems.add(new ProductItemInCart("001", "Adidas Stan Smith", R.drawable.ic_launcher_background, (float)35.5, "White", 1, 2500000));
        mProductItems.add(new ProductItemInCart("002", "Adidas Lite Racer", R.drawable.ic_launcher_background, (float)38.5, "Black", 2, 2000000));
    }

    public void toPayment(View view) {
        Intent switchActivityIntent = new Intent(this, PaymentActivity.class);
        startActivity(switchActivityIntent);
    }
}