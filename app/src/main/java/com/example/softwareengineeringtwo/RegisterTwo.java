package com.example.softwareengineeringtwo;

import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;

public class RegisterTwo extends AppCompatActivity {
EditText et_address;
RadioButton rb_male,rb_female;
CheckBox cb_privacy_policy;
Button btn_submit;
TextView tv_birthday;
Citizens citizen;
RadioGroup rg_gender;
DatabaseReference reff;
ProgressBar progBar_reg_two;
RelativeLayout prog_bar_holder_reg_two;
private static final String TAG ="RegisterTwo";
private DatePickerDialog.OnDateSetListener mDateSetListener;
private TextView mDisplayDate;
private String date;
private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        et_address = findViewById(R.id.et_address);
        tv_birthday = findViewById(R.id.tv_birthday);
        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);
        cb_privacy_policy = findViewById(R.id.cb_privacy_policy);
        btn_submit = findViewById(R.id.btn_submit);
        mDisplayDate = findViewById(R.id.tv_birthday);
        rg_gender = findViewById(R.id.rg_gender);
        progBar_reg_two = findViewById(R.id.prog_bar_reg_two);
        prog_bar_holder_reg_two = findViewById(R.id.prog_bar_holder_reg_two);

        //firebase
        citizen = new Citizens();
        reff = FirebaseDatabase.getInstance().getReference().child("Citizens");

        //Data Getter


        //checkbox
        cb_privacy_policy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(cb_privacy_policy.isChecked()){
                    btn_submit.setEnabled(true);
                }else{
                    btn_submit.setEnabled(false);
                }
            }
        });
        //birthday dialog
        tv_birthday.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(
                    RegisterTwo.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month +1;
                    Log.d(TAG,"onDataSet: mm/dd/yyyy" + year + "/" + month + "/" + day);
                    date = year + "-" + month + "-" + day;
                    mDisplayDate.setText(date);
                }
            };


        // Register Button Pushing Data in Database
        btn_submit.setOnClickListener(view -> {
            //gender radiobutton

            String firstName =getIntent().getStringExtra("FIRSTNAME");
            String lastName =getIntent().getStringExtra("LASTNAME");
            String contactNumber =getIntent().getStringExtra("CONTACTNUMBER");
            String Address = et_address.getText().toString().trim();
            String currentuserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

            if (rb_female.isChecked()){
                gender="FeMale";
            }
            if(rb_male.isChecked()){
                gender ="Male";
            }
            if (TextUtils.isEmpty(Address)) {
                et_address.setError("Address is required.");
                return;
            }

            if (TextUtils.isEmpty(gender)) {
                rb_male.setError("You need to choose a gender");
                rb_female.setError("You need to choose a gender");
                return;
            }

            if (TextUtils.isEmpty(date)) {
               mDisplayDate.setError("Birthday is required.");
                return;
            }

            progBar_reg_two.setVisibility(View.VISIBLE);
            prog_bar_holder_reg_two.setVisibility(View.VISIBLE);

                 //Add Personal Information into the firebase
                 String User_Role = "1";
                 String Vaccination_Status = "Unvaccinated";
                 citizen.setAddress(Address);
                 citizen.setbirthday(date);
                 citizen.setcontact_Number(contactNumber);
                 citizen.setfirst_Name(firstName);
                 citizen.setgender(gender);
                 citizen.setlast_Name(lastName);
                 citizen.setuser_Role(User_Role);
                 citizen.setvaccination_Status(Vaccination_Status);
                 reff.child(currentuserId).setValue(citizen);

                 //Intent To Entry Page
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Entry_Page.class);
                startActivity(intent);
                finish();

        });


    }
}