package com.example.clothingretailer;

import android.content.Context;

public class TestGenerator {
    public static void generate_test_db(Context context)
    {
        DBHandler handler = new DBHandler(context);

        // fill user table
        handler.add_user("username1", "password1", "firstname1", "lastname1", User.MALE, "email1", "phone1", "birthday1", "address1");
        handler.add_user("username2", "password2", "firstname2", "lastname2", User.MALE, "email2", "phone2", "birthday2", "address2");
        handler.add_user("username3", "password3", "firstname3", "lastname3", User.MALE, "email3", "phone3", "birthday3", "address3");
        handler.add_user("username4", "password4", "firstname4", "lastname4", User.MALE, "email4", "phone4", "birthday4", "address4");
        handler.add_user("username5", "password5", "firstname5", "lastname5", User.MALE, "email5", "phone5", "birthday5", "address5");
        handler.add_user("username6", "password6", "firstname6", "lastname6", User.FEMALE, "email6", "phone6", "birthday6", "address6");
        handler.add_user("username7", "password7", "firstname7", "lastname7", User.FEMALE, "email7", "phone7", "birthday7", "address7");
        handler.add_user("username8", "password8", "firstname8", "lastname8", User.FEMALE, "email8", "phone8", "birthday8", "address8");
        handler.add_user("username9", "password9", "firstname9", "lastname9", User.FEMALE, "email9", "phone9", "birthday9", "address9");

        // fill item table
        handler.add_item("itemname1", Item.MALE, "t-shirt", "dt1", "d1", "ht1", "h1", "link1", 1);
        handler.add_item("itemname2", Item.MALE, "type2", "dt2", "d2", "ht2", "h2", "path2", 2);
        handler.add_item("itemname3", Item.MALE, "type3", "dt3", "d3", "ht3", "h3", "path3", 3);
        handler.add_item("itemname4", Item.MALE, "type4", "dt4", "d4", "ht4", "h4", "path4", 4);
        handler.add_item("itemname5", Item.MALE, "type5", "dt5", "d5", "ht5", "h5", "path5", 5);
        handler.add_item("itemname6", Item.MALE, "type6", "dt6", "d6", "ht6", "h6", "path6", 6);
        handler.add_item("itemname7", Item.MALE, "type7", "dt7", "d7", "ht7", "h7", "path7", 7);
        handler.add_item("itemname8", Item.MALE, "type8", "dt8", "d8", "ht8", "h8", "path8", 8);
        handler.add_item("itemname9", Item.MALE, "type9", "dt9", "d9", "ht9", "h9", "path9", 9);

        // fill rating table
        handler.add_rating(1, "username1", 4.0F);
        handler.add_rating(1, "username5", 3.0F);
        handler.add_rating(2, "username2", 5.0F);
        handler.add_rating(2, "username3", 2.0F);
        handler.add_rating(2, "username4", 5.0F);
        handler.add_rating(3, "username2", 4.0F);
        handler.add_rating(3, "username6", 1.0F);
        handler.add_rating(4, "username5", 3.0F);
        handler.add_rating(3, "username5", 3.0F);

        // fill quantity table
        handler.add_quantity(1, "M", "Black", 21);
        handler.add_quantity(1, "L", "White", 22);
        handler.add_quantity(2, "M", "Red", 23);
        handler.add_quantity(2, "L", "Green", 24);
        handler.add_quantity(3, "M", "Blue", 25);
        handler.add_quantity(3, "L", "Brown", 26);
        handler.add_quantity(4, "M", "Blue", 27);
        handler.add_quantity(5, "M", "White", 28);
        handler.add_quantity(6, "M", "Green", 29);
        handler.add_quantity(6, "L", "Black", 30);
        handler.add_quantity(7, "M", "Black", 31);
        handler.add_quantity(8, "M", "Red", 32);
        handler.add_quantity(8, "L", "Cyan", 33);

        // fill like table
        handler.add_like("username1", 1);
        handler.add_like("username2", 2);
        handler.add_like("username2", 3);
        handler.add_like("username3", 3);
        handler.add_like("username3", 4);
        handler.add_like("username3", 5);
        handler.add_like("username4", 5);
        handler.add_like("username5", 6);
        handler.add_like("username4", 6);

        // fill cart table
        handler.add_cart("username1");
        handler.add_cart_item(1, 1, 3);
        handler.add_cart_item(1, 2, 2);
        handler.add_cart_item(1, 3, 5);
        handler.add_order(1, 100, 10, "cash", 1);

        handler.add_cart("username1");
        handler.add_cart_item(2, 4, 1);
        handler.add_cart_item(2, 5, 2);
        handler.add_cart_item(2, 6, 2);
        handler.add_order(2, 60, 5, "cash", 1);

        handler.add_cart("username2");
        handler.add_cart_item(3, 4, 2);
        handler.add_cart_item(3, 2, 2);
        handler.add_cart_item(3, 1, 4);
        handler.add_order(3, 70, 8, "cash", 1);

        handler.add_cart("username3");
        handler.add_cart_item(4, 1, 5);
        handler.add_cart_item(4, 7, 1);
        handler.add_cart_item(4, 8, 2);
        handler.add_order(4, 80, 10, "cash", 1);





        handler.close_DB();
    }
}
