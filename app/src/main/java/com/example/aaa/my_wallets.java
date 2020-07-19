package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class my_wallets extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallets);
        mRecyclerView = findViewById(R.id.WalletsRecyclerView);
        new FireBaseDatabaseHelper().readWallets(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Wallet> wallets, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, my_wallets.this,
                        wallets, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        ImageView back1 = findViewById(R.id.imageView26);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(my_wallets.this , MainPage.class);
                startActivity(back1);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
        TextView back = findViewById(R.id.textView16);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(my_wallets.this , MainPage.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
    }
}