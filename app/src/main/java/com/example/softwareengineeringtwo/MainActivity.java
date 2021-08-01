package com.example.softwareengineeringtwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btn_nav_view;
    DatabaseReference reffCitizen,reffEstablishment;
    String UserRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView btnNav = findViewById(R.id.bottomNavigationView2);
        btnNav.setOnNavigationItemSelectedListener(navListener);
        TextView tv_tester = findViewById(R.id.tv_tester);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();


    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            Fragment selectFragment = null;

            switch (item.getItemId()) {
                case R.id.item1:
                    selectFragment = new HomeFragment();
                    break;
                case R.id.item2:
                    selectFragment = new QrFragment();
                    break;
                case R.id.item3:
                    selectFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectFragment).commit();
            return true;
        }
    };
}