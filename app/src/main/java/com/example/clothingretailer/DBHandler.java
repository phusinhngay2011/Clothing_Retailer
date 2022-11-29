package com.example.clothingretailer;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

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
    private static final String ITEM_PRICE = "ITEM_PRICE";

    private static final String RATING_TABLE = "USER_RATING";
    private static final String RATING_ITEM_ID = "item_id";
    private static final String RATING_USERNAME = "username";
    private static final String RATING_SCORE = "score";

    private static final String QUANTITY_TABLE = "ITEM_QUANTITY";
    private static final String QUANTITY_ID = "id";
    private static final String QUANTITY_SIZES = "sizes";
    private static final String QUANTITY_COLORS = "colors";
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
    private static final String CI_ITEM_COUNT = "item_count";

    private static final String ORDER_TABLE = "ORDER_TABLE";
    private static final String ORDER_CART_ID = "cart_id";
    private static final String ORDER_TOTAL_PRICE = "total_price";
    private static final String ORDER_SHIPPING_FEE = "shipping_fee";
    private static final String ORDER_PAYMENT_METHOD = "payment_method";
    private static final String ORDER_PAID = "paid";
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
               + QUANTITY_SIZES + " TEXT, "
               + QUANTITY_COLORS + " TEXT, "
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
                + CI_ITEM_COUNT + " INTEGER, "
                + "PRIMARY KEY (" + CI_CART_ID + ", " + CI_ITEM_ID + "))",
        create_order_table_query = "CREATE TABLE " + ORDER_TABLE + " ("
                + ORDER_CART_ID + " INTEGER PRIMARY KEY, "
                + ORDER_TOTAL_PRICE + " INTEGER, "
                + ORDER_SHIPPING_FEE + " INTEGER, "
                + ORDER_PAYMENT_METHOD + " TEXT, "
                + ORDER_PAID + " INTEGER)";

        sqLiteDatabase.execSQL(create_user_table_query);
        sqLiteDatabase.execSQL(create_item_table_query);
        sqLiteDatabase.execSQL(create_rating_table_query);
        sqLiteDatabase.execSQL(create_quantity_table_query);
        sqLiteDatabase.execSQL(create_like_table_query);
        sqLiteDatabase.execSQL(create_cart_table_query);
        sqLiteDatabase.execSQL(create_ci_table_query);
        sqLiteDatabase.execSQL(create_order_table_query);

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

    public void add_user(String username, String password, String firstname, String lastname, int gender, String email, String phone, String birthday, String address)
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
        this.write_db.insert(USER_TABLE, null, values);
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
}
