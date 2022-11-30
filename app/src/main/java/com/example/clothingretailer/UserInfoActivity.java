package com.example.clothingretailer;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {

    private Button mChangeButton;
    private Button mLogOutButton;
    private ImageButton mBackButton;
    private EditText et_username;
    private EditText et_firstname;
    private EditText et_lastname;
    RadioGroup rg_gender;
    private EditText et_dob;
    private EditText et_email;
    private EditText et_address;
    private EditText et_phone;
    private EditText et_new_pw;
    private EditText et_confirm_new_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_user_info);
        mChangeButton = findViewById(R.id.change_button);
        mLogOutButton = findViewById(R.id.log_out_button);
        mBackButton = findViewById(R.id.back_button_userinfo);
        et_username = findViewById(R.id.edittext_username);
        et_firstname = findViewById(R.id.edittext_firstname);
        et_lastname = findViewById(R.id.edittext_lastname);
        rg_gender = findViewById(R.id.radiogroup_gender);
        et_dob = findViewById(R.id.edittext_dob);
        et_email = findViewById(R.id.edittext_email);
        et_address = findViewById(R.id.edittext_address);
        et_phone = findViewById(R.id.edittext_phone);
        et_new_pw = findViewById(R.id.edittext_new_password);
        et_confirm_new_pw = findViewById(R.id.edittext_confirm_new_password);
        setupUserInfoView();


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void setupUserInfoView()
    {
        if (GlobalVars.current_user == null || GlobalVars.logged_in == false)
        {
            et_username.setText("");
            et_firstname.setText("");
            et_lastname.setText("");
            rg_gender.check(0);
            et_dob.setText("");
            et_email.setText("");
            et_address.setText("");
            et_phone.setText("");
            et_new_pw.setText("");
            et_confirm_new_pw.setText("");
        }
        else
        {
            et_username.setText(GlobalVars.current_user.getUsername());
            et_firstname.setText(GlobalVars.current_user.getFirstname());
            et_lastname.setText(GlobalVars.current_user.getLastname());
            int gender, id;
            gender = GlobalVars.current_user.getGender();
            if (gender == User.MALE || gender == User.FEMALE)
                id = gender - 1;
            else
                id = 2;
            rg_gender.check(id);
            et_dob.setText(GlobalVars.current_user.getBirthday());
            et_email.setText(GlobalVars.current_user.getEmail());
            et_address.setText(GlobalVars.current_user.getAddress());
            et_phone.setText(GlobalVars.current_user.getPhone());
            et_new_pw.setText(GlobalVars.current_user.getPassword());
            et_confirm_new_pw.setText(GlobalVars.current_user.getPassword());
        }
    }
}