package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Map;

public class SignUpGeneralInfoActivity extends AppCompatActivity {
    private EditText firstnameSignupET;
    private EditText lastnameSignupET;
    private EditText birthdaySignupET;
    private EditText addressSignupET;
    private Button nextSignupBtn;
    private RadioGroup genderSignupRG;
    private RadioButton maleSignupRB;
    private RadioButton femaleSignupRB;
    private RadioButton customSignupRB;
    private final int validAge = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_general_info);

        GenerateFindViewById_sign_up_general_inf();
        User usr = getUserInfomationTemporary();

        firstnameSignupET.setText(usr.getFirstname());
        lastnameSignupET.setText(usr.getLastname());
        birthdaySignupET.setText(usr.getBirthday());
        addressSignupET.setText(usr.getAddress());

    }
    private void GenerateFindViewById_sign_up_general_inf() {
        firstnameSignupET = (EditText) findViewById(R.id.firstnameSignupET);
        lastnameSignupET = findViewById(R.id.lastnameSignupET);
        birthdaySignupET = findViewById(R.id.birthdaySignupET);
        addressSignupET = findViewById(R.id.addressSignupET);
        nextSignupBtn = findViewById(R.id.nextSignup);
        genderSignupRG = findViewById(R.id.genderSignupRG);
        maleSignupRB = findViewById(R.id.maleGenderSignupRB);
        femaleSignupRB = findViewById(R.id.femaleGenderSignupRB);
        customSignupRB = findViewById(R.id.customGenderSignupRB);
    }

    public void popUpDatePicker(View view){
        try {
            birthdaySignupET = findViewById(R.id.birthdaySignupET);
            final Calendar c = Calendar.getInstance();

            int _year = c.get(Calendar.YEAR);
            int _month = c.get(Calendar.MONTH);
            int _day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    SignUpGeneralInfoActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            int diff = _year - year;
                            if (monthOfYear > _month ||
                                    (monthOfYear == _month && dayOfMonth > _day))
                                diff--;
                            if (diff < validAge)
                                Toast.makeText(SignUpGeneralInfoActivity.this, "Invalid Age!", Toast.LENGTH_SHORT).show();
                            else
                                birthdaySignupET.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    },
                    _year - 13, _month, _day);
            datePickerDialog.show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void toSignUpMain(View view){
        try{
            String firstname = firstnameSignupET.getText().toString();
            String lastname = lastnameSignupET.getText().toString();
            String birthday = birthdaySignupET.getText().toString();
            String address = addressSignupET.getText().toString();
            int genderType = genderSignupRG.indexOfChild(
                    findViewById(genderSignupRG.getCheckedRadioButtonId())
            );
            StringHdr nameCheck = new StringHdr(firstname);
            String mess = nameCheck.validName();
            if(!mess.equals(""))
            {
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }
            nameCheck.setStr(lastname);
            mess = nameCheck.validName();
            if(!mess.equals(""))
            {
                Toast.makeText(getApplicationContext(), mess, Toast.LENGTH_SHORT).show();
                return;
            }


            if(birthday.equals("")){
                Toast.makeText(getApplicationContext(), "Birthday can not left blank", Toast.LENGTH_SHORT).show();
                return;
            }

            if(address.equals("")){
                Toast.makeText(getApplicationContext(), "Address can not left blank", Toast.LENGTH_SHORT).show();
                return;
            }

            User old = getUserInfomationTemporary();
            User usr = new User(old.getUsername(), firstname, lastname, genderType,
                    old.getEmail(), old.getPhone(), birthday, address, old.getPassword());
            //Toast.makeText(getApplicationContext(), usr.toString(), Toast.LENGTH_SHORT).show();

            // Lưu thông tin user tạm
            saveUserInfomationTemporary(usr);

            Intent switchActivityIntent = new Intent(this, SignUpMainInfoActivity.class);
            startActivity(switchActivityIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void toSignIn(View view){
        SharedPreferences.Editor editor = getSharedPreferences(
                PREFERENCES_NAME, MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        Intent switchActivityIntent = new Intent(this, SignInMainActivity.class);
        startActivity(switchActivityIntent);
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
        editor.putString(PASSWORD_KEY, usr.getPassword().toString());
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
        usr.setPassword(getInfo.getString(PASSWORD_KEY, ""));


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