package com.example.aaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class create_wallet extends AppCompatActivity {
    private EditText Wallet_Name;
    private EditText Section1;
    private EditText Section2;
    private EditText Section3;
    private Button Create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_wallet);
        Wallet_Name = findViewById(R.id.editTextTextPersonName2);
        Section1 = findViewById(R.id.editTextTextPersonName3);
        Section2 = findViewById(R.id.editTextTextPersonName4);
        Section3 = findViewById(R.id.editTextTextPersonName5);

        Create = findViewById(R.id.button3);
        Button Next = findViewById(R.id.button2);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Next = new Intent(create_wallet.this , my_wallets.class);
                startActivity(Next);
            }
        });

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(create_wallet.this, "لقد تم إنشاء محفظة جديدة بنجاح!", Toast.LENGTH_LONG).show();
                Wallet wallet = new Wallet();
                wallet.setName(Wallet_Name.getText().toString());
                wallet.setSectionsNames(Section1.getText().toString());

                   /*String result = wallet.getsectionNames();
                   String [] result1 = result.split(",");
                   for (int x = 0; x<result1.length ; x++)*/
                new FireBaseDatabaseHelper().addWallet(wallet, new FireBaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Wallet> wallets, List<String> keys) {
                        Toast.makeText(create_wallet.this, "لقد تم إنشاء محفظة جديدة بنجاح!", Toast.LENGTH_LONG).show();
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

            }
        });

        ImageView back1 = findViewById(R.id.imageView26);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(create_wallet.this , MainPage.class);
                startActivity(back1);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });
        TextView back = findViewById(R.id.textView16);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(create_wallet.this , MainPage.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right , R.anim.slide_out_left);
            }
        });

    }
}