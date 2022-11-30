package com.example.clothingretailer;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private static ArrayList<Item> mFavoriteItems;
    private RecyclerView mRecyclerView;
    private FavoriteItemAdapter mFavoriteItemAdapter;
    private static LinearLayout mEmptyFavoriteMessage;
    private ImageButton mBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mBackButton = findViewById(R.id.back_button_favorites);
        mRecyclerView = findViewById(R.id.recyclerView_favorite);
        mEmptyFavoriteMessage = findViewById(R.id.empty_favorite_message);
        mFavoriteItems = new ArrayList<Item>();
        loadFavoriteItems();

        mFavoriteItemAdapter = new FavoriteItemAdapter(this, mFavoriteItems);
        mRecyclerView.setAdapter(mFavoriteItemAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        checkEmpty();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }


    public static void checkEmpty() {
        if (mFavoriteItems.size() == 0) mEmptyFavoriteMessage.setVisibility(View.VISIBLE);
        else mEmptyFavoriteMessage.setVisibility(View.INVISIBLE);
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

    private void loadFavoriteItems() {
        // Replace by loading data from DB
        mFavoriteItems.add(new Item(1, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", null, null, null, null, String.valueOf(R.drawable.clothing_ex_details_2), 2999000));
        mFavoriteItems.add(new Item(2, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", null, null, null, null, String.valueOf(R.drawable.clothing_ex_details_1), 2999000));
        mFavoriteItems.add(new Item(3, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", null, null, null, null, String.valueOf(R.drawable.clothing_ex_details_3), 2999000));
        mFavoriteItems.add(new Item(4, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", null, null, null, null, String.valueOf(R.drawable.clothing_ex_details_4), 2999000));
        mFavoriteItems.add(new Item(5, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", null, null, null, null, String.valueOf(R.drawable.clothing_ex_details_5), 2999000));
    }

}