package com.example.clothingretailer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

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
            GenerateFindViewById_sign_up_general_inf();
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

            User usr = new User(firstname, lastname, genderType, birthday, address);
            Toast.makeText(getApplicationContext(), usr.toString(), Toast.LENGTH_SHORT).show();

            Intent switchActivityIntent = new Intent(this, SignUpMainInfoActivity.class);
            startActivity(switchActivityIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void toSignIn(View view){
        Intent switchActivityIntent = new Intent(this, SignInMainActivity.class);
        startActivity(switchActivityIntent);
    }



}