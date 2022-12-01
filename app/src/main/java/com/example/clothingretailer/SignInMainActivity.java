package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class SignInMainActivity extends AppCompatActivity {

    private EditText passwordSigninET;
    private EditText usernameSigninET;
    private DBHandler dbHandler = null;
    private CheckBox rmbMeCheckBox;

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

        rememberMeHandler();
        if (dbHandler == null)
        {
            this.dbHandler = new DBHandler(getApplicationContext());
            //dbHandler.add_user("admin123", "Password_123", "admin", "admin", 1, "admin@mail.com", "0900009990", "01/01/2002", "address");
        }
        //handler.close_DB();
    }

    private void cleanTemporaryMem() {
        SharedPreferences.Editor editor = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
    }

    private void rememberMeHandler() {
        SharedPreferences getInfo = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE);
        Boolean isRmb = getInfo.getBoolean(REMEMBER_ME_KEY, true);
        Toast.makeText(getApplicationContext(), isRmb.toString(), Toast.LENGTH_SHORT).show();
        if(isRmb && GlobalVars.current_user != null && GlobalVars.logged_in == true){
            usernameSigninET.setText(GlobalVars.current_user.getUsername());
            passwordSigninET.setText(GlobalVars.current_user.getPassword());
        }
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
        rmbMeCheckBox = findViewById(R.id.remeberMe);
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
            SharedPreferences.Editor editor = getSharedPreferences(
                    PREFERENCES_NAME, MODE_PRIVATE).edit();
            if(rmbMeCheckBox.isChecked())
                editor.putBoolean(REMEMBER_ME_KEY, true);
            else
                editor.putBoolean(REMEMBER_ME_KEY, false);
            editor.apply();
            Intent toHomeViewIntent = new Intent(this, MainActivity.class);
            startActivity(toHomeViewIntent);
        }
    }


    public void onForgotPasword(View view) {

    }

    public void onFacebookSigninOption(View view) {

    }

    public void onGoogleSigninOption(View view) {
    }

    private String PREFERENCES_NAME = "USER_TEMP";
    private String REMEMBER_ME_KEY = "rmb";
}