package com.example.softwareengineeringtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {
    EditText et_firstname,et_lastname,et_address,et_age,et_barangay,et_phonenumber,et_email,et_password,et_confirm_password;
    RadioButton rb_male,rb_female;
    CheckBox cb_privacy_policy;
    FirebaseAuth fAuth;
    Button btn_submit;
    DatabaseReference databaseCitizen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        databaseCitizen = FirebaseDatabase.getInstance().getReference("citizens");


        et_firstname = findViewById(R.id.et_firstname);
        et_lastname = findViewById(R.id.et_lastname);
        et_phonenumber = findViewById(R.id.et_phonenumber);
        et_email = findViewById(R.id.et_email);
        et_confirm_password =  findViewById(R.id.et_confirm_password);
        et_password = findViewById(R.id.et_password);
        btn_submit = findViewById(R.id.btn_submit);
        fAuth = FirebaseAuth.getInstance();


        btn_submit.setOnClickListener(view -> {

            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            String firstName = et_firstname.getText().toString().trim();
            String lastName = et_lastname.getText().toString().trim();
            String confirmPassword = et_password.getText().toString().trim();


            if (TextUtils.isEmpty(email)) {
                et_email.setError("Email is Required");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                et_password.setError("Password is Required");
                return;
            }

//            if (phoneNumber != 0) {
//                et_phonenumber.setError("Phone Number is required.");
//                return;
//            }
            if (TextUtils.isEmpty(firstName)) {
                et_firstname.setError("First Name is required.");
                return;
            }
            if (TextUtils.isEmpty(lastName)) {
                et_lastname.setError("Last Name is required.");
                return;
            }
            if (TextUtils.isEmpty(lastName) | !password.equals(confirmPassword)) {
                et_confirm_password.setError("Confirm Password is empty or do not match.");
                return;
            }
            Toast.makeText(RegisterActivity.this,"Register Successful",Toast.LENGTH_SHORT).show();
            fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),RegisterTwo.class));
                        finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
