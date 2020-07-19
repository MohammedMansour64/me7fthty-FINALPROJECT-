package com.example.aaa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class navigation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);


        TextView username_insert = findViewById(R.id.username_insert);

        username_insert.setText("Mohammed");

        username_insert.setText(getIntent().getStringExtra("username"));


    }
}
