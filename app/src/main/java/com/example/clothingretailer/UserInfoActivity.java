package com.example.clothingretailer;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

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

    private LinearLayout changePWLayout;
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
        changePWLayout = findViewById(R.id.ChangePWLayout);
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
            try {
                new AlertDialog.Builder(UserInfoActivity.this)
                        .setTitle("Requires login")
                        .setMessage("You need to log in to use more features")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton("Log in", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent switchActivityIntent = new Intent(UserInfoActivity.this, SignInMainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })

                        .setNegativeButton("Back to homepage", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent switchActivityIntent = new Intent(UserInfoActivity.this, MainActivity.class);
                                startActivity(switchActivityIntent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
            catch (Exception e){
                Toast.makeText(UserInfoActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
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

            if(id == 0)
                id = R.id.maleGenderUserRB;
            else if(id == 1)
                id =R.id.femaleGenderUserRB;
            else
                id = R.id.customGenderUserRB;
            rg_gender.check(id);

            et_dob.setText(GlobalVars.current_user.getBirthday());
            et_email.setText(GlobalVars.current_user.getEmail());
            et_address.setText(GlobalVars.current_user.getAddress());
            et_phone.setText(GlobalVars.current_user.getPhone());
        }
    }
    public void popUpDatePicker(View view){
        try {
            et_dob = findViewById(R.id.birthdaySignupET);
            final Calendar c = Calendar.getInstance();

            int _year = c.get(Calendar.YEAR);
            int _month = c.get(Calendar.MONTH);
            int _day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    UserInfoActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            int diff = _year - year;
                            if (monthOfYear > _month ||
                                    (monthOfYear == _month && dayOfMonth > _day))
                                diff--;
                            if (diff < 13)
                                Toast.makeText(UserInfoActivity.this, "Invalid Age!", Toast.LENGTH_SHORT).show();
                            else
                                et_dob.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    },
                    _year - 13, _month, _day);
            datePickerDialog.show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onEnableEdit(View view) {
        et_username.setEnabled(true);
        et_firstname.setEnabled(true);
        et_lastname.setEnabled(true);
        et_dob.setEnabled(true);
        et_email.setEnabled(true);
        et_address.setEnabled(true);
        et_phone.setEnabled(true);
        et_new_pw.setEnabled(true);
        et_confirm_new_pw.setEnabled(true);
    }

    public void toSignIn(View view) {
        Intent switchActivityIntent = new Intent(this, SignInMainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void onSaveChange(View view) {
        String firstname = et_firstname.getText().toString();
        String lastname = et_lastname.getText().toString();
        String birthday = et_dob.getText().toString();
        String address = et_address.getText().toString();
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();
        String password = et_new_pw.getText().toString();
        String repassword = et_confirm_new_pw.getText().toString();

        int genderType = rg_gender.indexOfChild(
                findViewById(rg_gender.getCheckedRadioButtonId())
        ) + 1;
        if (genderType == 3)
            genderType = 0;

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
        StringHdr check = new StringHdr(username);
        mess = check.validUsername();
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


        GlobalVars.current_user.setUsername(username);
        GlobalVars.current_user.setFirstname(firstname);
        GlobalVars.current_user.setLastname(lastname);
        GlobalVars.current_user.setBirthday(birthday);
        GlobalVars.current_user.setAddress(address);
        GlobalVars.current_user.setEmail(email);
        GlobalVars.current_user.setPhone(phone);
        GlobalVars.current_user.setPassword(repassword);

        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void displayChangePW(View view) {
        showOrHideFrameLayout(changePWLayout);
    }
    public void showOrHideFrameLayout(LinearLayout layout) {
        if (layout.getVisibility() == View.VISIBLE)
            layout.setVisibility(View.GONE);
        else
            layout.setVisibility(View.VISIBLE);
    }
}