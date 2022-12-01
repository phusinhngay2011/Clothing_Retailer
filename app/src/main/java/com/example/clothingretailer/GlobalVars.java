package com.example.clothingretailer;

import java.util.ArrayList;

public class GlobalVars {
    public static User current_user;
    public static boolean logged_in;
    public static Item selected_item;
    public static ArrayList<Item> current_cart_items = new ArrayList<Item>();
    public static ArrayList<String> current_cart_sizes = new ArrayList<String>();
    public static ArrayList<String> current_cart_colors = new ArrayList<String>();
    public static ArrayList<Integer> current_cart_item_counts = new ArrayList<Integer>();
    public static Item quick_cart_item;
    public static String quick_cart_size;
    public static String quick_cart_color;
    public static String quick_cart_count;
    public static boolean quick_purchase_mode = false;
}
