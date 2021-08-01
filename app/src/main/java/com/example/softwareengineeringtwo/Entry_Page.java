package com.example.softwareengineeringtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Entry_Page extends AppCompatActivity {
    TextInputEditText et_email,et_password;
    Button btn_login;
    TextView tv_register,tv_forgot_password;
    ProgressBar progBar_entry;
    RelativeLayout prog_bar_holder_entry;
    private FirebaseAuth fAuth;
    DatabaseReference reffCitizen,reffEstablishment;
    private static final String TAG = "EmailPassword";
    String UserRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        tv_register = findViewById(R.id.tv_register);
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        fAuth = FirebaseAuth.getInstance();
        FirebaseUser user = fAuth.getCurrentUser();
        progBar_entry = findViewById(R.id.prog_bar_entry);
        prog_bar_holder_entry = findViewById(R.id.prog_bar_holder_entry);

        // Go to Register Activity
        tv_register.setOnClickListener(view -> {
            progBar_entry.setVisibility(View.VISIBLE);
            prog_bar_holder_entry.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        //Forgot Password
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Password Reset");
                passwordResetDialog.setMessage("Enter your email to recieve a reset password link");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Entry_Page.this,"Reset link sent to your email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                Toast.makeText(Entry_Page.this,"Email is not registered0"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });

            // Log in button
            btn_login.setOnClickListener(view -> {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {
                    et_email.setError("Email is Missing.");
                    et_password.setError("Password is Missing.");
                    return;
                }
                //Authenticate ng email at password
                fAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progBar_entry.setVisibility(View.VISIBLE);
                                        prog_bar_holder_entry.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(Entry_Page.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }

                            }
                        });
            });


    }
    //If user is already login go straight sa dashboard
    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}