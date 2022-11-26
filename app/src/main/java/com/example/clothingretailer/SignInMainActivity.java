package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInMainActivity extends AppCompatActivity {

    private EditText passwordSigninET;
    private EditText usernameSigninET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_main);
        GenerateFindViewById_sign_in_main();
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
    }

    public void onRememberMe(View view) {

    }

    public void onForgotPasword(View view) {

    }

    public void onFacebookSigninOption(View view) {

    }

    public void onGoogleSigninOption(View view) {
    }

}