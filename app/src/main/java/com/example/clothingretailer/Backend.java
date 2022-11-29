package com.example.clothingretailer;

public class Backend {
    private boolean logged_in;
    private User current_user;

    // used in sign up button's onClick
    public void create_credential(String username, String password, String firstname, String lastname, int gender, String email, String phone, String birthday, String address)
    {
        // search for username in db (DBHandler)
        //
        // if available
        //     return error code
        // else
        //     add 2 database (DBHandler)
        //     return success code
    }

    // used in sign in button's onClick
    public boolean verify_credential(String username, String password)
    {
        // User user = Search for usr & pw in db (DBHandler)
        //
        // if available
        //     this->current_user = user
        //     this->logged_in = true
        //     return success code
        // else
        //     return error code

        return false;
    }
}
