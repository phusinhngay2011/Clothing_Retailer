package com.example.clothingretailer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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
        checkIfLogin();
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
        mFavoriteItems = GlobalVars.current_favorite_items;

    }

    public void checkIfLogin(){
        if (GlobalVars.current_user == null || GlobalVars.logged_in == false)
        {
            try {
                new AlertDialog.Builder(FavoritesActivity.this)
                        .setTitle("Requires login")
                        .setMessage("You need to log in to use more features")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent switchActivityIntent = new Intent(FavoritesActivity.this, SignInMainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })

                        .setNegativeButton("Back to homepage", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent switchActivityIntent = new Intent(FavoritesActivity.this, MainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }


}