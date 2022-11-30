package com.example.clothingretailer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static ArrayList<Item> mHomeItems1;
    private static ArrayList<Item> mHomeItems2;
    private static ArrayList<Item> mHomeItems3;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;
    private HomeItemAdapter mHomeItemAdapter;
    private DrawerLayout mDrawerLayout;
    private ImageButton mMenuButton;
    private ImageButton mCartButton;
    private DBHandler dbHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView1 = findViewById(R.id.recyclerview1_home);
        mRecyclerView2 = findViewById(R.id.recyclerview2_home);
        mRecyclerView3 = findViewById(R.id.recyclerview3_home);
        mHomeItems1 = new ArrayList<Item>();
        mHomeItems2 = new ArrayList<Item>();
        mHomeItems3 = new ArrayList<Item>();
        loadHomeItems();

        // Need to be 3 Adapter, but now just 1 for demo
        mHomeItemAdapter = new HomeItemAdapter(this, mHomeItems1);
        mRecyclerView1.setAdapter(mHomeItemAdapter);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView2.setAdapter(mHomeItemAdapter);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView3.setAdapter(mHomeItemAdapter);
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        mMenuButton = findViewById(R.id.nav_button_homepage);
        mDrawerLayout = findViewById(R.id.drawer_layout_home);
        NavigationView navigationView = findViewById(R.id.nav_view_home);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        //menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        switch (menuItem.getItemId()) {

                            case R.id.nav_userinfo: {
                                toUserInfo();
                                break;
                            }

                            case R.id.nav_favorite: {
                                toFavorites();
                                break;
                            }

                            case R.id.nav_about: {
                                toAboutUs();
                                break;
                            }
                            case R.id.nav_logout:{
                                toSignIn();
                                break;
                            }
                        }
                        return true;
                    }
                });

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        navigationView.setCheckedItem(R.id.nav_home);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
    }

    private void toSignIn() {
        GlobalVars.current_user = null;
        GlobalVars.logged_in = false;
        Intent switchActivityIntent = new Intent(this, SignInMainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toUserInfo() {
        Intent switchActivityIntent = new Intent(this, UserInfoActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toFavorites() {
        Intent switchActivityIntent = new Intent(this, FavoritesActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toAboutUs() {
        Intent switchActivityIntent = new Intent(this, AboutUsActivity.class);
        startActivity(switchActivityIntent);
    }



    public void loadHomeItems() {
        mHomeItems1.add(new Item(001, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description title", "No description", "", "", String.valueOf(R.drawable.clothing_ex_details_2), 2999000));
        mHomeItems1.add(new Item(001, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description title", "No description", "", "", String.valueOf(R.drawable.clothing_ex_details_2), 2999000));
        mHomeItems1.add(new Item(001, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description title", "No description", "", "", String.valueOf(R.drawable.clothing_ex_details_2), 2999000));
        mHomeItems1.add(new Item(001, "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description title", "No description", "", "", String.valueOf(R.drawable.clothing_ex_details_2), 2999000));
//        mHomeItems1.add(new Item("001", "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description", String.valueOf(R.drawable.clothing_ex_details_1), 2999000, 4.75, 316));
//        mHomeItems1.add(new Item("001", "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description", String.valueOf(R.drawable.clothing_ex_details_3), 2999000, 4.75, 316));
//        mHomeItems1.add(new Item("001", "Adidas Stan Smith All White Christmas 2022 Limited", 0, "Shoes", "No description", String.valueOf(R.drawable.clothing_ex_details_4), 2999000, 4.75, 316));

    }


    public void toSearchActivity(View view) {
        Intent switchActivityIntent = new Intent(this, SearchActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toShoppingCart(View view) {
        Intent switchActivityIntent = new Intent(this, ShoppingCartActivity.class);
        startActivity(switchActivityIntent);
    }

    public void toProductDetails(View view) {
        Intent switchActivityIntent = new Intent(this, ProductDetailsActivity.class);
        startActivity(switchActivityIntent);
    }
}

