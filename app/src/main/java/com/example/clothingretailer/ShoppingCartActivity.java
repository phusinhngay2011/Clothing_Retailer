package com.example.clothingretailer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartActivity extends AppCompatActivity {
    private static ArrayList<ProductItemInCart> mProductItems;
    private RecyclerView mRecyclerView;
    private ProductItemAdapter mProductItemAdapter;
    private static TextView mTotalPrice;
    private static TextView mShippingFee;
    private static ImageView mImageEmpty;
    private ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        mBackButton = findViewById(R.id.back_button_cart);
        mRecyclerView = findViewById(R.id.recyclerView_cart);
        mTotalPrice = findViewById(R.id.total_price);
        mShippingFee = findViewById(R.id.shipping_fee);

        mImageEmpty = findViewById(R.id.image_empty_cart);




        // chay cai nay 1 lan de generate test db
        // TestGenerator.generate_test_db(getApplicationContext());

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "Shopping cart onResume called");
        mProductItems = new ArrayList<ProductItemInCart>();
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
        if (len % 3 == 0)
            res = res.substring(1);
        return res;
    }

    private void loadProductItems() {
        // Replace by loading data from DB
        for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
        {
            Item item = GlobalVars.current_cart_items.get(i);
            List<String> urls = StringHdr.getURLImgs(item.getImage_path());
            mProductItems.add(new ProductItemInCart(item.getId(), item.getName(), urls.get(0),
                    GlobalVars.current_cart_sizes.get(i), GlobalVars.current_cart_colors.get(i), (int) GlobalVars.current_cart_item_counts.get(i),
                    item.getPrice()));
        }
    }

    public void toHome(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toPayment(View view) {
        savePriceTemporary();
        Intent switchActivityIntent = new Intent(this, PaymentActivity.class);
        startActivity(switchActivityIntent);
    }

    private void savePriceTemporary(){
        SharedPreferences.Editor editor = getSharedPreferences(
                PREFERENCES_PRICE, MODE_PRIVATE).edit();
        editor.putString(SUBTOTAL_PRICE, mTotalPrice.getText().toString());
        editor.putString(SHIPPING_PRICE, mShippingFee.getText().toString());
        editor.apply();
    }

    private String PREFERENCES_PRICE = "TOTAL_PRICE_TEMP";
    private String SUBTOTAL_PRICE = "st";
    private String SHIPPING_PRICE = "sp";

}