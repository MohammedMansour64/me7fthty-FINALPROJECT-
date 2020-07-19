package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);




        Button createAnAccount = findViewById(R.id.createAnAccount);
        Button SignIn = findViewById(R.id.SignIn);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SignIn = new Intent(Sign.this,sign_in.class);
                startActivity(SignIn);
            }
        });

        createAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAnAccount = new Intent(Sign.this,sign_up.class);
                startActivity(createAnAccount);
            }
        });
    }
}