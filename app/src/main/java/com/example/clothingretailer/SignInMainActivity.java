package com.example.clothingretailer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SignInMainActivity extends AppCompatActivity {

    private EditText passwordSigninET;
    private EditText usernameSigninET;
    private DBHandler dbHandler = null;
    private CheckBox rmbMeCheckBox;
    private CheckBox hideOrShowPW;

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
        //Log.d("tag", "main on create called");
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
        try{
            Boolean isRmb = getInfo.getBoolean(REMEMBER_ME_KEY, true);
            if(isRmb){
                String usr = getInfo.getString(USERNAME, "");
                String pw = getInfo.getString(PASSWORD, "");
                usernameSigninET.setText(usr);
                passwordSigninET.setText(pw);
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Log.d("tag", "main on pause called");
        if (dbHandler != null)
        {
            dbHandler.close_DB();
            dbHandler = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.d("tag", "main on resume called");
        if (dbHandler == null)
        {
            dbHandler = new DBHandler(getApplicationContext());
        }
    }


    private void GenerateFindViewById_sign_in_main() {
        passwordSigninET = findViewById(R.id.passwordSignin);
        usernameSigninET = findViewById(R.id.usernameSignin);
        rmbMeCheckBox = findViewById(R.id.remeberMe);
        hideOrShowPW = findViewById(R.id.hideOrShowPW);
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

            // Load saved user favorites
            ArrayList<UserLike> savedUserLikes = this.dbHandler.search_like(username);
            if (savedUserLikes != null && savedUserLikes.size() > 0) {
                for (int i = 0; i < savedUserLikes.size(); i++ ) {
                    GlobalVars.current_favorite_items.add(this.dbHandler.search_item_by_id(savedUserLikes.get(i).getItem_id()).get(0));
                }
            }


            SharedPreferences.Editor editor = getSharedPreferences(
                    PREFERENCES_NAME, MODE_PRIVATE).edit();
            if(rmbMeCheckBox.isChecked()){
                editor.putBoolean(REMEMBER_ME_KEY, true);
                editor.putString(USERNAME, username);
                editor.putString(PASSWORD, password);
            }
            else{
                editor.clear();
                editor.putBoolean(REMEMBER_ME_KEY, false);
            }
            editor.apply();
            Intent toHomeViewIntent = new Intent(this, MainActivity.class);
            startActivity(toHomeViewIntent);
        }
    }


    public void onHideOrShowPW(View view) {
        Boolean isHide = hideOrShowPW.isChecked();
        if(isHide){
            passwordSigninET.setTransformationMethod(null);
            hideOrShowPW.setBackground(getResources().getDrawable(R.drawable.ic_baseline_remove_red_eye_24));
        }
        else{
            passwordSigninET.setTransformationMethod(null);
            passwordSigninET.setTransformationMethod(new PasswordTransformationMethod());
            hideOrShowPW.setBackground(getResources().getDrawable(R.drawable.ic_invisible));
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
    private String USERNAME = "usr";
    private String PASSWORD = "pw";

}