package com.example.aaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private FirebaseAuth mAuth;
    private DrawerLayout drawer;


    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        final ImageView create_wallet = findViewById(R.id.imageView17);
        create_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create_wallet = new Intent(MainPage.this , com.example.aaa.create_wallet.class);
                startActivity(create_wallet);
            }
        });
        final ImageView my_wallets = findViewById(R.id.imageView22);
        my_wallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent my_wallets = new Intent(MainPage.this , com.example.aaa.my_wallets.class);
                startActivity(my_wallets);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer , toolbar ,
                R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(user == null) {
            startActivity(new Intent(this , Sign.class));
            finish();
        }



    }
    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser == null) {
                Intent intent = new Intent(MainPage.this, sign_in.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_account:
                Intent acc = new Intent(MainPage.this , AccountPage.class);
                startActivity(acc);
                break;
            case R.id.nav_calendar:
                Intent calendar = new Intent(MainPage.this , CalendarPage.class);
                startActivity(calendar);
                break;

            case R.id.nav_contact:
                Intent contact = new Intent(MainPage.this , ContactPage.class);
                startActivity(contact);
                break;

            case R.id.nav_settings:
                Intent settings = new Intent(MainPage.this , SettingsPage.class);
                startActivity(settings);
                break;

            case R.id.nav_logout:
                Intent logout = new Intent(MainPage.this , Sign.class);
                startActivity(logout);
                mAuth.signOut();
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
                break;


        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  else {
            super.onBackPressed();
        }

    }
}