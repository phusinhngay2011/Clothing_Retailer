package com.example.clothingretailer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {
    private static ArrayList<Item> mFavoriteItems;
    private RecyclerView mRecyclerView;
    private FavoriteItemAdapter mFavoriteItemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        mRecyclerView = findViewById(R.id.recyclerView_favorite);
        mFavoriteItems = new ArrayList<Item>();
        loadFavoriteItems();

        mFavoriteItemAdapter = new FavoriteItemAdapter(this, mFavoriteItems);
        mRecyclerView.setAdapter(mFavoriteItemAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        Fragment emptyFav = new FavoritesBlankFragment();
//        replaceFragment(emptyFav);
    }

//    private void replaceFragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.framelayoutFavorites, fragment);
//        fragmentTransaction.commit();
//    }
//
//    public void toHome(View view) {
//
//    }

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