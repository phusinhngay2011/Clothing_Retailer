package com.example.clothingretailer;

import android.os.Bundle;
import android.util.Log;
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
        Log.d("tag", "FavoritesActivity onResume called");
        mFavoriteItems = new ArrayList<Item>();
        loadFavoriteItems();
        mFavoriteItemAdapter = new FavoriteItemAdapter(this, mFavoriteItems);
        mRecyclerView.setAdapter(mFavoriteItemAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        checkEmpty();
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
        if (len % 3 == 0)
            res = res.substring(1);
        return res;
    }

    private void loadFavoriteItems() {
        // Replace by loading data from DB
//        for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
//        {
//            Item item = GlobalVars.current_cart_items.get(i);
//            List<String> urls = StringHdr.getURLImgs(item.getImage_path());
//            mFavoriteItems.add(new ProductItemInCart(item.getId(), item.getName(), urls.get(0),
//                    GlobalVars.current_cart_sizes.get(i), GlobalVars.current_cart_colors.get(i), (int) GlobalVars.current_cart_item_counts.get(i),
//                    item.getPrice()));
//        }
        DBHandler dbHandler = new DBHandler(this);
        mFavoriteItems = GlobalVars.current_favorite_items;

    }

}