package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignUpWelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_welcome);
    }
    public void toSignUpGeneralInf(View view){
        Intent switchActivityIntent = new Intent(this, SignUpGeneralInfoActivity.class);
        startActivity(switchActivityIntent);
    }
}