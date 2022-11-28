package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class UserInfoActivity extends AppCompatActivity {

    private Button mChangeButton;
    private Button mLogOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_user_info);
        mChangeButton = findViewById(R.id.change_button);
        mLogOutButton = findViewById(R.id.log_out_button);


    }
}