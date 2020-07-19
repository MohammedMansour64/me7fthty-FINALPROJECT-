package com.example.aaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SectionsPage extends AppCompatActivity {
    private RecyclerView fRecyclerView;
    private Context x;
    private ArrayList<Wallet> mWalletList = new ArrayList<>();
    private ArrayList<Sections> mSectionsList = new ArrayList<>();
    private List<String> mKey;

    DatabaseReference Sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        fRecyclerView = findViewById(R.id.Sections_RecyclerView);
//        Query query = FirebaseDatabase.getInstance().getReference("Sections").orderByChild("SectionWallet2").startAt("Section1").endAt("Section3");
//        Query query1 = FirebaseDatabase.getInstance().getReference("Wallets").orderByChild("SectionsNames");
//        query1.addValueEventListener(valueEventListener);
//        query.addValueEventListener(valueEventListener);
        fRecyclerView.setLayoutManager(new GridLayoutManager(x, 2));
        pleaseAdapter a = new pleaseAdapter(mWalletList, mKey, x);
        fRecyclerView.setAdapter(a);

        new FireBaseDatabaseHelper().readWallets(new FireBaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Wallet> wallets, List<String> keys) {
                new RecyclerView_Config().setConfig1(fRecyclerView, SectionsPage.this,
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
                Intent back1 = new Intent(SectionsPage.this, my_wallets.class);
                startActivity(back1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        TextView back = findViewById(R.id.textView16);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(SectionsPage.this, my_wallets.class);
                startActivity(back);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            List<String> keys = new ArrayList<>();
//            mSectionsList.clear();
//            for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
//                keys.add(keyNode.getKey());
//                Sections section = keyNode.getValue(Sections.class);
//                mSectionsList.add(section);
//
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    };
//
//    ValueEventListener valueEventListener1 = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            List<String> keys = new ArrayList<>();
//            mWalletList.clear();
//            for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
//                keys.add(keyNode.getKey());
//                Wallet wallet = keyNode.getValue(Wallet.class);
//                mWalletList.add(wallet);
//
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//    };
}