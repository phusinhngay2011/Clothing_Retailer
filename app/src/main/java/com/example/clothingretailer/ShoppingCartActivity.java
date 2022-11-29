package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
        DBHandler handler = new DBHandler(getApplicationContext());
        // ham add_user chua kiem tra trung nen may ong add 1 lan thoi
        //handler.add_user("username14", "password14", "firstname14", "lastname14", User.MALE, "email14", "phone14", "birthday14", "address14");
        ArrayList<User> res = handler.search_user("username3", "password3");
        ArrayList<User> res2 = handler.search_user("username3", null);
        ArrayList<User> res3 = handler.search_user("username15", "password15");
        if (res != null)
        {
            System.out.println(res.size());
            for (int i = 0; i < res.size(); i++)
                System.out.println(res.get(i).toString());
        }
        if (res2 != null)
        {
            System.out.println(res2.size());
            for (int i = 0; i < res2.size(); i++)
                System.out.println(res2.get(i).toString());
        }
        if (res3 != null)
        {
            System.out.println(res3.size());
            for (int i = 0; i < res3.size(); i++)
                System.out.println(res3.get(i).toString());
        }
        handler.close_DB();
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