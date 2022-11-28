package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

public class SignUpMainInfoActivity extends AppCompatActivity {
    EditText usernameSignupET;
    EditText emailSignupET;
    EditText phoneSignupET;
    EditText passwordSignupET;
    EditText repasswordSignupET;
    Button finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_main_info);
        GenerateFindViewById_sign_up_main_inf();

        User usr =  getUserInfomationTemporary();
        usernameSignupET.setText(usr.getUsername());
        emailSignupET.setText(usr.getEmail());
        phoneSignupET.setText(usr.getPhone());

    }

    private void GenerateFindViewById_sign_up_main_inf() {
        usernameSignupET = (EditText) findViewById(R.id.usernameSignupET);
        emailSignupET = (EditText) findViewById(R.id.emailSignupET);
        phoneSignupET = (EditText) findViewById(R.id.phoneSignupET);
        passwordSignupET = (EditText) findViewById(R.id.passwordSignupET);
        repasswordSignupET = (EditText) findViewById(R.id.repasswordSignupET);
        finishBtn = (Button) findViewById(R.id.finishSignupBtn);
    }


    public void toSignUpGeneralInf(View view){
        try {
            String username = usernameSignupET.getText().toString();
            String email = emailSignupET.getText().toString();
            String phone = phoneSignupET.getText().toString();

            User usr = getUserInfomationTemporary();
            usr.setUsername(username);
            usr.setEmail(email);
            usr.setPhone(phone);

            saveUserInfomationTemporary(usr);

            Toast.makeText(this, usr.toString(), Toast.LENGTH_LONG).show();
            Intent switchActivityIntent = new Intent(this, SignUpGeneralInfoActivity.class);
            startActivity(switchActivityIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void  toSignUpCompleteness(View view){
        try{
            String username = usernameSignupET.getText().toString();
            String email = emailSignupET.getText().toString();
            String phone = phoneSignupET.getText().toString();
            String password = passwordSignupET.getText().toString();
            String repassword = repasswordSignupET.getText().toString();

            // Check username

            StringHdr check = new StringHdr(username);
            String mess = check.validUsername();
            if (!mess.equals("")){
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }
            // Check email
            check.setStr(email);
            mess = check.validEmail();
            if(!mess.equals("")){
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }
            // Check phone number

            check.setStr(phone);
            mess = check.validPhone();
            if(!mess.equals("")){
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }

            // Check password

            check.setStr(password);
            mess = check.validPassword();
            if(!mess.equals("")){
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }

            //check re-password

            if(!password.equals(repassword)){
                mess = "Re-password doesn't match";
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }


            User usr = getUserInfomationTemporary();
            usr.setUsername(username);
            usr.setEmail(email);
            usr.setPhone(phone);

            usr.setPassword((new StringHdr(password)).encodePassword());
            // Luu vao database


            // Xoa du lieu tam
            SharedPreferences.Editor editor = getSharedPreferences(
                    PREFERENCES_NAME, MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();


            Intent switchActivityIntent = new Intent(this, SignUpCompletenessActivity.class);
            startActivity(switchActivityIntent);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUserInfomationTemporary(User usr){
        SharedPreferences.Editor editor = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.putString(USERNAME_KEY, usr.getUsername());
        editor.putString(FIRSTNAME_KEY, usr.getFirstname());
        editor.putString(LASTNAME_KEY, usr.getLastname());
        editor.putInt(GENDER_KEY, usr.getGender());
        editor.putString(EMAIL_KEY, usr.getEmail());
        editor.putString(PHONE_KEY, usr.getPhone());
        editor.putString(BIRTHDAY_KEY, usr.getBirthday());
        editor.putString(ADDRESS_KEY, usr.getAddress());

        editor.apply();
    }

    private User getUserInfomationTemporary(){
        User usr = new User();
        SharedPreferences getInfo = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE);

        usr.setUsername(getInfo.getString(USERNAME_KEY, ""));
        usr.setFirstname(getInfo.getString(FIRSTNAME_KEY, ""));
        usr.setLastname(getInfo.getString(LASTNAME_KEY, ""));
        usr.setGender(getInfo.getInt(GENDER_KEY, 0));
        usr.setEmail(getInfo.getString(EMAIL_KEY, ""));
        usr.setPhone(getInfo.getString(PHONE_KEY, ""));
        usr.setBirthday(getInfo.getString(BIRTHDAY_KEY,""));
        usr.setAddress(getInfo.getString(ADDRESS_KEY, ""));

        return usr;
    }
    private String PREFERENCES_NAME = "USER_TEMP";
    private String USERNAME_KEY = "usrn";
    private String FIRSTNAME_KEY = "fn";
    private String LASTNAME_KEY = "ln";
    private String GENDER_KEY = "gd";
    private String EMAIL_KEY = "em";
    private String PHONE_KEY = "pn";
    private String BIRTHDAY_KEY = "bd";
    private String ADDRESS_KEY = "ad";
    private String PASSWORD_KEY = "pw";

}