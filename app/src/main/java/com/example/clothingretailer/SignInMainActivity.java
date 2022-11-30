package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class SignInMainActivity extends AppCompatActivity {

    private EditText passwordSigninET;
    private EditText usernameSigninET;
    private DBHandler dbHandler = null;

    public SignInMainActivity() {
        super();
    }

    public SignInMainActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main);
        GenerateFindViewById_sign_in_main();

        // Xoa du lieu tam
        SharedPreferences.Editor editor = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        if (dbHandler == null)
        {
            this.dbHandler = new DBHandler(getApplicationContext());
            //dbHandler.add_user("admin123", "Password_123", "admin", "admin", 1, "admin@mail.com", "0900009990", "01/01/2002", "address");
        }
        //handler.close_DB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("tag", "on pause called");
        if (dbHandler != null)
        {
            dbHandler.close_DB();
            dbHandler = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("tag", "on resume called");
        if (dbHandler == null)
        {
            dbHandler = new DBHandler(getApplicationContext());
        }
    }


    private void GenerateFindViewById_sign_in_main() {
        passwordSigninET = findViewById(R.id.passwordSignin);
        usernameSigninET = findViewById(R.id.usernameSignin);
    }

    public void toSignUp(View view){
        Intent switchActivityIntent = new Intent(this, SignUpWelcomeActivity.class);
        startActivity(switchActivityIntent);
    }


    public void onUserLogin(View view) {
        String username = usernameSigninET.getText().toString().toLowerCase();
        String password = passwordSigninET.getText().toString();
        StringHdr unCheck = new StringHdr(username);
        StringHdr pwCheck = new StringHdr(password);
        String mess = unCheck.validUsername();
        if(!mess.equals("")){
            Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
            return;
        }
        mess = pwCheck.validPassword();
        if(!mess.equals("")){
            Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<User> res = this.dbHandler.search_user(username, password);
        if (res == null || res.size() <= 0)
        {
            Toast.makeText(getApplicationContext(), "Invalid credentials, please try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            GlobalVars.current_user = res.get(0);
            GlobalVars.logged_in = true;
            Intent toHomeViewIntent = new Intent(this, MainActivity.class);
            startActivity(toHomeViewIntent);
        }
    }

    public void onRememberMe(View view) {

    }

    public void onForgotPasword(View view) {

    }

    public void onFacebookSigninOption(View view) {

    }

    public void onGoogleSigninOption(View view) {
    }

    private String PREFERENCES_NAME = "USER_TEMP";
}