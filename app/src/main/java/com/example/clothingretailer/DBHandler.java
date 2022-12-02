package com.example.clothingretailer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "AppDB";
    private static final int DB_VER = 1;

    private static final String USER_TABLE = "USER";
    private static final String USER_ID = "id";
    private static final String USER_USERNAME = "username";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRSTNAME = "firstname";
    private static final String USER_LASTNAME = "lastname";
    private static final String USER_GENDER = "gender";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE = "phone";
    private static final String USER_BIRTHDAY = "birthday";
    private static final String USER_ADDRESS = "address";

    private static final String ITEM_TABLE = "ITEM";
    private static final String ITEM_ID = "id";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_GENDER = "gender";
    private static final String ITEM_TYPE = "type";
    private static final String ITEM_DESCRIPTION_TITLE = "description_title";
    private static final String ITEM_DESCRIPTION = "description";
    private static final String ITEM_HIGHLIGHT_TITLE = "highlight_title";
    private static final String ITEM_HIGHLIGHT = "highlight";
    private static final String ITEM_IMAGE_PATH = "image_path";
    private static final String ITEM_PRICE = "price";

    private static final String RATING_TABLE = "USER_RATING";
    private static final String RATING_ITEM_ID = "item_id";
    private static final String RATING_USERNAME = "username";
    private static final String RATING_SCORE = "score";

    private static final String QUANTITY_TABLE = "ITEM_QUANTITY";
    private static final String QUANTITY_ID = "id";
    private static final String QUANTITY_SIZE = "size";
    private static final String QUANTITY_COLOR = "color";
    private static final String QUANTITY_COUNT = "count";

    private static final String LIKE_TABLE = "USER_LIKE";
    private static final String LIKE_USERNAME = "username";
    private static final String LIKE_ITEM_ID = "item_id";

    private static final String CART_TABLE = "CART";
    private static final String CART_ID = "id";
    private static final String CART_USERNAME = "username";

    private static final String CART_ITEM_TABLE = "CART_ITEM";
    private static final String CI_CART_ID = "cart_id";
    private static final String CI_ITEM_ID = "item_id";
    private static final String CI_SIZE = "size";
    private static final String CI_COLOR = "color";
    private static final String CI_ITEM_COUNT = "item_count";

    private static final String ORDER_TABLE = "ORDER_TABLE";
    private static final String ORDER_CART_ID = "cart_id";
    private static final String ORDER_TOTAL_PRICE = "total_price";
    private static final String ORDER_SHIPPING_FEE = "shipping_fee";
    private static final String ORDER_PAYMENT_METHOD = "payment_method";
    private static final String ORDER_PAID = "paid";

    private static final String NEW_ARRIVALS_TABLE = "NA_TABLE";
    private static final String NA_ITEM_ID = "item_id";

    private static final String TRENDING_FOR_MEN_TABLE = "TFM_TABLE";
    private static final String TFM_ITEM_ID = "item_id";

    private static final String TRENDING_FOR_WOMEN_TABLE = "TFW_TABLE";
    private static final String TFW_ITEM_ID = "item_id";
    SQLiteDatabase read_db = null;
    SQLiteDatabase write_db = null;


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_user_table_query = "CREATE TABLE " + USER_TABLE + " ("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_USERNAME + " TEXT, "
                + USER_PASSWORD + " TEXT, "
                + USER_FIRSTNAME + " TEXT, "
                + USER_LASTNAME + " TEXT, "
                + USER_GENDER + " INTEGER, "
                + USER_EMAIL + " TEXT, "
                + USER_PHONE + " TEXT, "
                + USER_BIRTHDAY + " TEXT, "
                + USER_ADDRESS + " TEXT)",
        create_item_table_query = "CREATE TABLE " + ITEM_TABLE + " ("
                + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ITEM_NAME + " TEXT, "
                + ITEM_GENDER + " INT, "
                + ITEM_TYPE + " TEXT, "
                + ITEM_DESCRIPTION_TITLE + " TEXT, "
                + ITEM_DESCRIPTION + " TEXT, "
                + ITEM_HIGHLIGHT_TITLE + " TEXT, "
                + ITEM_HIGHLIGHT + " TEXT, "
                + ITEM_IMAGE_PATH + " TEXT, "
                + ITEM_PRICE + " INT)",
        create_rating_table_query = "CREATE TABLE " + RATING_TABLE + " ("
                + RATING_ITEM_ID + " INTEGER, "
                + RATING_USERNAME + " TEXT, "
                + RATING_SCORE + " REAL, "
                + "PRIMARY KEY (" + RATING_ITEM_ID + ", " + RATING_USERNAME + "))",
        create_quantity_table_query = "CREATE TABLE " + QUANTITY_TABLE + " ("
               + QUANTITY_ID + " INTEGER PRIMARY KEY, "
               + QUANTITY_SIZE + " TEXT, "
               + QUANTITY_COLOR + " TEXT, "
               + QUANTITY_COUNT + " INTEGER)",
        create_like_table_query = "CREATE TABLE " + LIKE_TABLE + " ("
                + LIKE_USERNAME + " TEXT, "
                + LIKE_ITEM_ID + " INTEGER, "
                + "PRIMARY KEY (" + LIKE_USERNAME + ", " + LIKE_ITEM_ID + "))",
        create_cart_table_query = "CREATE TABLE " + CART_TABLE + " ("
                + CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CART_USERNAME + " TEXT)",
        create_ci_table_query = "CREATE TABLE " + CART_ITEM_TABLE + " ("
                + CI_CART_ID + " INTEGER, "
                + CI_ITEM_ID + " INTEGER, "
                + CI_SIZE + " TEXT, "
                + CI_COLOR + " TEXT, "
                + CI_ITEM_COUNT + " INTEGER, "
                + "PRIMARY KEY (" + CI_CART_ID + ", " + CI_ITEM_ID + ", " + CI_SIZE + ", " + CI_COLOR + "))",
        create_order_table_query = "CREATE TABLE " + ORDER_TABLE + " ("
                + ORDER_CART_ID + " INTEGER PRIMARY KEY, "
                + ORDER_TOTAL_PRICE + " INTEGER, "
                + ORDER_SHIPPING_FEE + " INTEGER, "
                + ORDER_PAYMENT_METHOD + " TEXT, "
                + ORDER_PAID + " INTEGER)",
        create_new_arrival_table_query = "CREATE TABLE " + NEW_ARRIVALS_TABLE + " ("
                + NA_ITEM_ID + " INTEGER PRIMARY KEY)",
        create_trending_for_men_table_query = "CREATE TABLE " + TRENDING_FOR_MEN_TABLE + " ("
                + TFM_ITEM_ID + " INTEGER PRIMARY KEY)",
        create_trending_for_women_table_query = "CREATE TABLE " + TRENDING_FOR_WOMEN_TABLE + " ("
                + TFW_ITEM_ID + " INTEGER PRIMARY KEY)";

        sqLiteDatabase.execSQL(create_user_table_query);
        sqLiteDatabase.execSQL(create_item_table_query);
        sqLiteDatabase.execSQL(create_rating_table_query);
        sqLiteDatabase.execSQL(create_quantity_table_query);
        sqLiteDatabase.execSQL(create_like_table_query);
        sqLiteDatabase.execSQL(create_cart_table_query);
        sqLiteDatabase.execSQL(create_ci_table_query);
        sqLiteDatabase.execSQL(create_order_table_query);
        sqLiteDatabase.execSQL(create_new_arrival_table_query);
        sqLiteDatabase.execSQL(create_trending_for_men_table_query);
        sqLiteDatabase.execSQL(create_trending_for_women_table_query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RATING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QUANTITY_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LIKE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CART_ITEM_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NEW_ARRIVALS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRENDING_FOR_MEN_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TRENDING_FOR_WOMEN_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void open_DB_for_read()
    {
        if (this.read_db == null)
        {
            this.read_db = this.getReadableDatabase();
        }
    }

    public void open_DB_for_write()
    {
        if (this.write_db == null){
            this.write_db = this.getWritableDatabase();
        }
    }

    public void close_DB()
    {
        if (read_db != null)
            read_db.close();
        if (write_db != null)
            write_db.close();
    }

    public User add_user(String username, String password, String firstname, String lastname, int gender, String email, String phone, String birthday, String address)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(USER_USERNAME, username);
        values.put(USER_PASSWORD, password);
        values.put(USER_FIRSTNAME, firstname);
        values.put(USER_LASTNAME, lastname);
        values.put(USER_GENDER, gender);
        values.put(USER_EMAIL, email);
        values.put(USER_PHONE, phone);
        values.put(USER_BIRTHDAY, birthday);
        values.put(USER_ADDRESS, address);
        long res = this.write_db.insert(USER_TABLE, null, values);
        if (res == -1)
        {
            return null;
        }

        ArrayList<User> users = search_user(username, password);
        if (users != null)
            if (users.size() > 0)
                return users.get(0);

        return null;
    }

    @SuppressLint("Range")
    public ArrayList<User> search_user(@Nullable String username, @Nullable String password)
    {
        open_DB_for_read();
        String selection = null;
        String[] selectionArgs = null;
        if (username == null && password == null)
        {
            selection = null;
            selectionArgs = null;
        }
        else
        {
            selection = (username != null ? USER_USERNAME + " = ?" : "") + (username != null && password != null ? " AND " : "") + (password != null ? USER_PASSWORD + " = ?" : "");
            if (username != null && password != null)
            {
                selectionArgs = new String[] {username, password};
            }
            else if (username != null && password == null)
            {
                selectionArgs = new String[] {username};
            }
            else if (username == null && password != null)
            {
                selectionArgs = new String[] {password};
            }
        }
        Cursor cursor = read_db.query(USER_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<User> result = new ArrayList<User>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do
                {
                    String tmp_username, tmp_password, tmp_firstname, tmp_lastname, tmp_email, tmp_phone, tmp_birthday, tmp_address;
                    int tmp_id, tmp_gender;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(USER_ID));
                    tmp_username = cursor.getString(cursor.getColumnIndex(USER_USERNAME));
                    tmp_password = cursor.getString(cursor.getColumnIndex(USER_PASSWORD));
                    tmp_firstname = cursor.getString(cursor.getColumnIndex(USER_FIRSTNAME));
                    tmp_lastname = cursor.getString(cursor.getColumnIndex(USER_LASTNAME));
                    tmp_gender = cursor.getInt(cursor.getColumnIndex(USER_GENDER));
                    tmp_email = cursor.getString(cursor.getColumnIndex(USER_EMAIL));
                    tmp_phone = cursor.getString(cursor.getColumnIndex(USER_PHONE));
                    tmp_birthday = cursor.getString(cursor.getColumnIndex(USER_BIRTHDAY));
                    tmp_address = cursor.getString(cursor.getColumnIndex(USER_ADDRESS));
                    User tmp_user = new User(tmp_id, tmp_username, tmp_firstname, tmp_lastname, tmp_gender, tmp_email, tmp_phone, tmp_birthday, tmp_address, tmp_password);
                    result.add(tmp_user);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    public Item add_item(String name, int gender, String type, @Nullable String description_title, @Nullable String description, @Nullable String highlight_title, @Nullable String highlight, String image_path, int price)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(ITEM_NAME, name);
        values.put(ITEM_GENDER, gender);
        values.put(ITEM_TYPE, type);
        values.put(ITEM_DESCRIPTION_TITLE, description_title);
        values.put(ITEM_DESCRIPTION, description);
        values.put(ITEM_HIGHLIGHT_TITLE, highlight_title);
        values.put(ITEM_HIGHLIGHT, highlight);
        values.put(ITEM_IMAGE_PATH, image_path);
        values.put(ITEM_PRICE, price);
        long res = this.write_db.insert(ITEM_TABLE, null, values);
        if (res == -1)
            return null;

        ArrayList<Item> items = search_item(name, gender, type);
        if (items != null)
            if (items.size() > 0)
                return items.get(0);

        return null;
    }

    @SuppressLint("Range")
    public ArrayList<Item> search_item(@Nullable String name, int gender, @Nullable String type)
    {
        open_DB_for_read();
        if (name != null)
            name = name.toLowerCase();
        String selection = null;
        String[] selectionArgs = null;

        if (!(name == null && gender == Item.BOTH_GENDERS && type == null))
        {
            selection = (name != null ? "LOWER(" + ITEM_NAME + ") = ?" : "") + (name != null && gender != Item.BOTH_GENDERS ? " AND " : "") + (gender != Item.BOTH_GENDERS ? ITEM_GENDER + " = ?" : "")
                    + ((gender != Item.BOTH_GENDERS && type != null) || (name != null && type != null) ? " AND " : "") + (type != null ? ITEM_TYPE + " = ?" : "");
            int count = 0;
            count += (name != null ? 1 : 0) + (gender != Item.BOTH_GENDERS ? 1 : 0) + (type != null ? 1 : 0);
            //Log.d("count", String.valueOf(count));
            selectionArgs = new String[count];
            if (type != null)
            {
                selectionArgs[count - 1] = type;
                count--;
            }
            if (gender != Item.BOTH_GENDERS)
            {
                selectionArgs[count - 1] = String.valueOf(gender);
                count--;
            }
            if (name != null)
            {
                selectionArgs[count - 1] = name;
                count--;
            }
        }

        Cursor cursor = read_db.query(ITEM_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<Item> result = new ArrayList<Item>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id, tmp_gender, tmp_price;
                    String tmp_name, tmp_type, tmp_description_title, tmp_description, tmp_highlight_title, tmp_highlight, tmp_image_path;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(ITEM_ID));
                    tmp_name = cursor.getString(cursor.getColumnIndex(ITEM_NAME));
                    tmp_gender = cursor.getInt(cursor.getColumnIndex(ITEM_GENDER));
                    tmp_type = cursor.getString(cursor.getColumnIndex(ITEM_TYPE));
                    tmp_description_title = cursor.getString(cursor.getColumnIndex(ITEM_DESCRIPTION_TITLE));
                    tmp_description = cursor.getString(cursor.getColumnIndex(ITEM_DESCRIPTION));
                    tmp_highlight_title = cursor.getString(cursor.getColumnIndex(ITEM_HIGHLIGHT_TITLE));
                    tmp_highlight = cursor.getString(cursor.getColumnIndex(ITEM_HIGHLIGHT));
                    tmp_image_path = cursor.getString(cursor.getColumnIndex(ITEM_IMAGE_PATH));
                    tmp_price = cursor.getInt(cursor.getColumnIndex(ITEM_PRICE));
                    Item tmp_item = new Item(tmp_id, tmp_name, tmp_gender, tmp_type, tmp_description_title, tmp_description, tmp_highlight_title, tmp_highlight, tmp_image_path, tmp_price);
                    result.add(tmp_item);
                } while (cursor.moveToNext());
            }
            cursor.close();

            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    // item_id = -1 => match all items
    @SuppressLint("Range")
    public ArrayList<Item> search_item_by_id(int item_id)
    {
        open_DB_for_read();
        String selection = (item_id != -1 ? ITEM_ID + " = ?" : null);
        String[] selectionArgs = (item_id != -1 ? new String[] {String.valueOf(item_id)} : null);

        Cursor cursor = read_db.query(ITEM_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<Item> res = new ArrayList<Item>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id, tmp_gender, tmp_price;
                    String tmp_name, tmp_type, tmp_description_title, tmp_description, tmp_highlight_title, tmp_highlight, tmp_image_path;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(ITEM_ID));
                    tmp_name = cursor.getString(cursor.getColumnIndex(ITEM_NAME));
                    tmp_gender = cursor.getInt(cursor.getColumnIndex(ITEM_GENDER));
                    tmp_type = cursor.getString(cursor.getColumnIndex(ITEM_TYPE));
                    tmp_description_title = cursor.getString(cursor.getColumnIndex(ITEM_DESCRIPTION_TITLE));
                    tmp_description = cursor.getString(cursor.getColumnIndex(ITEM_DESCRIPTION));
                    tmp_highlight_title = cursor.getString(cursor.getColumnIndex(ITEM_HIGHLIGHT_TITLE));
                    tmp_highlight = cursor.getString(cursor.getColumnIndex(ITEM_HIGHLIGHT));
                    tmp_image_path = cursor.getString(cursor.getColumnIndex(ITEM_IMAGE_PATH));
                    tmp_price = cursor.getInt(cursor.getColumnIndex(ITEM_PRICE));
                    Item tmp_item = new Item(tmp_id, tmp_name, tmp_gender, tmp_type, tmp_description_title, tmp_description, tmp_highlight_title, tmp_highlight, tmp_image_path, tmp_price);
                    res.add(tmp_item);
                } while (cursor.moveToNext());
            }
            cursor.close();

            if (res.size() > 0)
                return res;
            else
                return null;
        }
        return null;
    }

    public void add_rating(int item_id, String username, float rating_score)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(RATING_ITEM_ID, item_id);
        values.put(RATING_USERNAME, username);
        values.put(RATING_SCORE, rating_score);
        write_db.insert(RATING_TABLE, null, values);
    }

    // item_id = -1 => match all items
    // username = null => match all users
    @SuppressLint("Range")
    public ArrayList<UserRating> search_rating(int item_id, @Nullable String username)
    {
        open_DB_for_read();
        String selection = null;
        String[] selectionArgs = null;
        int count = 0;
        if (!(item_id == -1 && username == null))
        {
            selection = (item_id != -1 ? RATING_ITEM_ID +  " = ?" : "") + (item_id != -1 && username != null ? " AND " : "") + (username != null ? RATING_USERNAME + " = ?" : "");
            count += (item_id != -1 ? 1 : 0) + (username != null ? 1 : 0);
            selectionArgs = new String[count];
            if (username != null)
            {
                selectionArgs[count - 1] = username;
                count--;
            }
            if (item_id != -1)
            {
                selectionArgs[count - 1] = String.valueOf(item_id);
                count--;
            }
        }

        Cursor cursor = read_db.query(RATING_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<UserRating> result = new ArrayList<UserRating>();
        if (cursor != null)
        {
            if (cursor.getCount()> 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id;
                    String tmp_username;
                    float tmp_score;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(RATING_ITEM_ID));
                    tmp_username = cursor.getString(cursor.getColumnIndex(RATING_USERNAME));
                    tmp_score = cursor.getFloat(cursor.getColumnIndex(RATING_SCORE));
                    UserRating tmp_user_rating = new UserRating(tmp_id, tmp_username, tmp_score);
                    result.add(tmp_user_rating);

                } while (cursor.moveToNext());
            }
            cursor.close();

            if (result.size() > 0)
                return result;
            return null;
        }

        return null;
    }

    public void add_quantity(int item_id, @Nullable String size, @Nullable String color, int count)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(QUANTITY_ID, item_id);
        values.put(QUANTITY_SIZE, size);
        values.put(QUANTITY_COLOR, color);
        values.put(QUANTITY_COUNT, count);
        write_db.insert(QUANTITY_TABLE, null, values);
    }

    // item_id = -1 => match all items
    // size = null => match all sizes
    // color = null => match all colors
    @SuppressLint("Range")
    public ArrayList<ItemQuantity> search_quantity(int item_id, @Nullable String size, @Nullable String color)
    {
        open_DB_for_read();
        String selection = null;
        String[] selectionArgs = null;

        if (!(item_id == -1 && size == null && color == null))
        {
            selection = (item_id != -1 ? QUANTITY_ID + " = ?" : "") + (item_id != -1 && size != null ? " AND " : "") + (size != null ? QUANTITY_SIZE + " = ?" : "")
                    + ((item_id != -1 && color != null) || (size != null && color != null) ? " AND " : "") + (color != null ? QUANTITY_COLOR + " = ?" : "");
            int count = 0;
            count += (item_id != -1 ? 1 : 0) + (size != null ? 1 : 0) + (color != null ? 1 : 0);
            selectionArgs = new String[count];
            if (color != null)
            {
                selectionArgs[count - 1] = color;
                count--;
            }
            if (size != null)
            {
                selectionArgs[count - 1] = size;
                count--;
            }
            if (item_id != -1)
            {
                selectionArgs[count - 1] = String.valueOf(item_id);
                count--;
            }
        }

        Cursor cursor = read_db.query(QUANTITY_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<ItemQuantity> result = new ArrayList<ItemQuantity>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id, tmp_count;
                    String tmp_size, tmp_color;

                    tmp_id = cursor.getInt(cursor.getColumnIndex(QUANTITY_ID));
                    tmp_size = cursor.getString(cursor.getColumnIndex(QUANTITY_SIZE));
                    tmp_color = cursor.getString(cursor.getColumnIndex(QUANTITY_COLOR));
                    tmp_count = cursor.getInt(cursor.getColumnIndex(QUANTITY_COUNT));

                    ItemQuantity tmp_iq = new ItemQuantity(tmp_id, tmp_size, tmp_color, tmp_count);
                    result.add(tmp_iq);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    public void update_quantity(int item_id, @NonNull String size, @NonNull String color, int new_qty)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(QUANTITY_COUNT, new_qty);
        String selection = QUANTITY_ID + " = ? AND " + QUANTITY_SIZE + " = ? AND " + QUANTITY_COLOR + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(item_id), size, color};
        long tmp = write_db.update(QUANTITY_TABLE, values, selection, selectionArgs);
        //Log.d("selection", selection + item_id + size + color + new_qty);
        //Log.d("rows update", String.valueOf(tmp) + "  " + values);
    }

    public void add_like(String username, int item_id)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(LIKE_USERNAME, username);
        values.put(LIKE_ITEM_ID, item_id);
        write_db.insert(LIKE_TABLE, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<UserLike> search_like(@Nullable String username)
    {
        open_DB_for_read();
        String selection = (username != null ? LIKE_USERNAME + " = ?" : null);
        String[] selectionArgs = (username != null ? new String[] {username} : null);

        Cursor cursor = read_db.query(LIKE_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<UserLike> result = new ArrayList<UserLike>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    String tmp_username;
                    int tmp_item_id;
                    tmp_username = cursor.getString(cursor.getColumnIndex(LIKE_USERNAME));
                    tmp_item_id = cursor.getInt(cursor.getColumnIndex(LIKE_ITEM_ID));

                    UserLike tmp_user_like = new UserLike(tmp_username, tmp_item_id);
                    result.add(tmp_user_like);

                } while (cursor.moveToNext());
            }
            cursor.close();

            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    // username = null => match all username
    // item_id = -1 -> match all items
    public void delete_like(@Nullable String username, int item_id)
    {
        open_DB_for_write();
        String selection = null;
        String[] selectionArgs = null;

        if (!(username == null && item_id == -1))
        {
            selection = (username != null ? LIKE_USERNAME + " = ?": "") + (username != null && item_id != -1 ? " AND " : "") + (item_id != -1 ? LIKE_ITEM_ID + " = ?": "");
            int count = 0;
            count += (username != null ? 1 : 0) + (item_id != -1 ? 1 : 0);
            selectionArgs = new String[count];
            if (item_id != -1)
            {
                selectionArgs[count - 1] = String.valueOf(item_id);
                count--;
            }
            if (username != null)
            {
                selectionArgs[count - 1] = username;
                count--;
            }
        }

        int rows_deleted = write_db.delete(LIKE_TABLE, selection, selectionArgs);
    }

    public void remove_cart(int cart_id)
    {
        open_DB_for_write();
        String selection1 = CART_ID + " = ?", selection2 = CI_CART_ID + " = ?", selection3 = ORDER_CART_ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(cart_id)};
        write_db.delete(CART_ITEM_TABLE, selection2, selectionArgs);
        write_db.delete(ORDER_TABLE, selection3, selectionArgs);
        write_db.delete(CART_TABLE, selection1, selectionArgs);
    }

    public Cart add_cart(String username)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(CART_USERNAME, username);
        long res = write_db.insert(CART_TABLE, null, values);

        if (res == -1)
            return null;

        int largest_id_index = 0;
        int largest_id = 0;
        ArrayList<Cart> list = search_cart(username);
        if (list != null)
            if (list.size() > 0)
            {
                for (int i = 0; i < list.size(); i++)
                    if (list.get(i).getCart_id() > largest_id)
                    {
                        largest_id = list.get(i).getCart_id();
                        largest_id_index = i;
                    }
                return list.get(largest_id_index);
            }

        return null;
    }

    @SuppressLint("Range")
    public ArrayList<Cart> search_cart(@Nullable String username)
    {
        open_DB_for_read();
        String selection = (username != null ? CART_USERNAME + " = ?" : null);
        String[] selectionArgs = (username != null ? new String[] {username} : null);

        Cursor cursor = read_db.query(CART_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<Cart> result = new ArrayList<Cart>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    String tmp_username;
                    int tmp_cart_id;
                    tmp_cart_id = cursor.getInt(cursor.getColumnIndex(CART_ID));
                    tmp_username = cursor.getString(cursor.getColumnIndex(CART_USERNAME));

                    Cart tmp_cart = new Cart(tmp_cart_id, tmp_username);
                    result.add(tmp_cart);

                } while (cursor.moveToNext());
            }
            cursor.close();

            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    public void add_cart_item(int cart_id, int item_id, String size, String color, int item_count)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(CI_CART_ID, cart_id);
        values.put(CI_ITEM_ID, item_id);
        values.put(CI_SIZE, size);
        values.put(CI_COLOR, color);
        values.put(CI_ITEM_COUNT, item_count);
        write_db.insert(CART_ITEM_TABLE, null, values);
    }

    // cart_id = -1 => match all carts
    // item_id = -1 => match all items
    @SuppressLint("Range")
    public ArrayList<CartItem> search_cart_item(int cart_id, int item_id)
    {
        open_DB_for_read();
        String selection = null;
        String[] selectionArgs = null;

        if (!(cart_id == -1 && item_id == -1))
        {
            selection = (cart_id != -1 ? CI_CART_ID + " = ?" : "") + (cart_id != -1 && item_id != -1 ? " AND " : "") + (item_id != -1 ? CI_ITEM_ID + " = ?" : "");
            int count = 0;
            count += (cart_id != -1 ? 1 : 0) + (item_id != -1 ? 1 : 0);
            selectionArgs = new String[count];
            if (item_id != -1)
            {
                selectionArgs[count - 1] = String.valueOf(item_id);
                count--;
            }
            if (cart_id != -1)
            {
                selectionArgs[count - 1] = String.valueOf(cart_id);
                count--;
            }
        }

        Cursor cursor = read_db.query(CART_ITEM_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<CartItem> result = new ArrayList<CartItem>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_cart_id, tmp_item_id, tmp_count;
                    String tmp_size, tmp_color;
                    tmp_cart_id = cursor.getInt(cursor.getColumnIndex(CI_CART_ID));
                    tmp_item_id = cursor.getInt(cursor.getColumnIndex(CI_ITEM_ID));
                    tmp_count = cursor.getInt(cursor.getColumnIndex(CI_ITEM_COUNT));
                    tmp_size = cursor.getString(cursor.getColumnIndex(CI_SIZE));
                    tmp_color = cursor.getString(cursor.getColumnIndex(CI_COLOR));

                    CartItem tmp_ci = new CartItem(tmp_cart_id, tmp_item_id, tmp_size, tmp_color, tmp_count);
                    result.add(tmp_ci);

                } while (cursor.moveToNext());
            }
            cursor.close();

            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    public void add_order(int cart_id, int total_price, int shipping_fee, String payment_method, int paid)
    {
        open_DB_for_write();
        ContentValues values = new ContentValues();
        values.put(ORDER_CART_ID, cart_id);
        values.put(ORDER_TOTAL_PRICE, total_price);
        values.put(ORDER_SHIPPING_FEE, shipping_fee);
        values.put(ORDER_PAYMENT_METHOD, payment_method);
        values.put(ORDER_PAID, paid);
        write_db.insert(ORDER_TABLE, null, values);
    }

    @SuppressLint("Range")
    public ArrayList<Order> search_order(int cart_id)
    {
        open_DB_for_read();
        String selection = (cart_id != -1 ? ORDER_CART_ID + " = ?" : null);
        String[] selectionArgs = (cart_id != -1 ? new String[] {String.valueOf(cart_id)} : null);

        Cursor cursor = read_db.query(ORDER_TABLE, null, selection, selectionArgs, null, null, null);
        ArrayList<Order> result = new ArrayList<Order>();

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                do {
                    int tmp_cart_id, tmp_total_price, tmp_shipping_fee, tmp_paid;
                    String tmp_payment_method;
                    tmp_cart_id = cursor.getInt(cursor.getColumnIndex(ORDER_CART_ID));
                    tmp_total_price = cursor.getInt(cursor.getColumnIndex(ORDER_TOTAL_PRICE));
                    tmp_shipping_fee = cursor.getInt(cursor.getColumnIndex(ORDER_SHIPPING_FEE));
                    tmp_paid = cursor.getInt(cursor.getColumnIndex(ORDER_PAID));
                    tmp_payment_method = cursor.getString(cursor.getColumnIndex(ORDER_PAYMENT_METHOD));

                    Order tmp_order = new Order(tmp_cart_id, tmp_total_price, tmp_shipping_fee, tmp_payment_method, tmp_paid);
                    result.add(tmp_order);
                } while (cursor.moveToNext());
            }
            cursor.close();
            if (result.size() > 0)
                return result;
            else
                return null;
        }

        return null;
    }

    public void add_na_item(int item_id)
    {
        try {
            open_DB_for_write();
            ContentValues values = new ContentValues();
            values.put(NA_ITEM_ID, item_id);
            write_db.insert(NEW_ARRIVALS_TABLE, null, values);
        } catch (Exception e)
        {
            Log.d("exception:", e.toString());
        }
    }

    @SuppressLint("Range")
    public ArrayList<Item> get_na_items()
    {
        open_DB_for_read();
        ArrayList<Item> res = new ArrayList<Item>();

        Cursor cursor = read_db.query(NEW_ARRIVALS_TABLE, null, null, null, null, null, null);

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(NA_ITEM_ID));
                    ArrayList<Item> tmp_list = search_item_by_id(tmp_id);
                    if (tmp_list != null)
                    {
                        if (tmp_list.size() > 0)
                        {
                            for (int i = 0; i < tmp_list.size(); i++)
                                res.add(tmp_list.get(i));
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();

            if (res.size() > 0)
                return res;
            else
                return null;
        }

        return null;
    }


    public void add_tfm_item(int item_id)
    {
        try {
            open_DB_for_write();
            ContentValues values = new ContentValues();
            values.put(TFM_ITEM_ID, item_id);
            write_db.insert(TRENDING_FOR_MEN_TABLE, null, values);
        } catch (Exception e)
        {
            Log.d("exception:", e.toString());
        }
    }

    @SuppressLint("Range")
    public ArrayList<Item> get_tfm_items()
    {
        open_DB_for_read();
        ArrayList<Item> res = new ArrayList<Item>();

        Cursor cursor = read_db.query(TRENDING_FOR_MEN_TABLE, null, null, null, null, null, null);

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(TFM_ITEM_ID));
                    ArrayList<Item> tmp_list = search_item_by_id(tmp_id);
                    if (tmp_list != null)
                    {
                        if (tmp_list.size() > 0)
                        {
                            for (int i = 0; i < tmp_list.size(); i++)
                                res.add(tmp_list.get(i));
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();

            if (res.size() > 0)
                return res;
            else
                return null;
        }

        return null;
    }


    public void add_tfw_item(int item_id)
    {
        try {
            open_DB_for_write();
            ContentValues values = new ContentValues();
            values.put(TFW_ITEM_ID, item_id);
            write_db.insert(TRENDING_FOR_WOMEN_TABLE, null, values);
        } catch (Exception e)
        {
            Log.d("exception:", e.toString());
        }
    }
    @SuppressLint("Range")
    public ArrayList<Item> get_tfw_items()
    {
        open_DB_for_read();
        ArrayList<Item> res = new ArrayList<Item>();

        Cursor cursor = read_db.query(TRENDING_FOR_WOMEN_TABLE, null, null, null, null, null, null);

        if (cursor != null)
        {
            if (cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                do {
                    int tmp_id;
                    tmp_id = cursor.getInt(cursor.getColumnIndex(TFW_ITEM_ID));
                    ArrayList<Item> tmp_list = search_item_by_id(tmp_id);
                    if (tmp_list != null)
                    {
                        if (tmp_list.size() > 0)
                        {
                            for (int i = 0; i < tmp_list.size(); i++)
                                res.add(tmp_list.get(i));
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();

            if (res.size() > 0)
                return res;
            else
                return null;
        }

        return null;
    }
}
