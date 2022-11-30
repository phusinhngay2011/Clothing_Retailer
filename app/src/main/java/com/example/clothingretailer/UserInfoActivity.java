package com.example.clothingretailer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    private Button mChangeButton;
    private Button mLogOutButton;
    private ImageButton mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_user_info);
        mChangeButton = findViewById(R.id.change_button);
        mLogOutButton = findViewById(R.id.log_out_button);
        mBackButton = findViewById(R.id.back_button_userinfo);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}