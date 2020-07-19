package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        ImageView back1 = findViewById(R.id.imageView26);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(ContactPage.this , MainPage.class);
                startActivity(back1);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
        TextView back = findViewById(R.id.textView16);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ContactPage.this , MainPage.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }
}