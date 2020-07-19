package com.example.aaa;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseDatabaseHelper {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mReferenceWallets;
    private DatabaseReference mReferenceSections;
    private List<Wallet> wallets = new ArrayList<>();
    private List<Sections> section = new ArrayList<>();

    public interface DataStatus {
        // to connect between two arrays
        void DataIsLoaded(List<Wallet> wallets, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public interface DataStatus1 {
        // to connect between two arrays
        void DataIsLoaded(List<Sections> sections, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }

    public FireBaseDatabaseHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceWallets = mDataBase.getReference("Wallets");
        mDataBase = FirebaseDatabase.getInstance();
        mReferenceSections = mDataBase.getReference("Sections");


    }


    public void readWallets(final DataStatus dataStatus) {

        mReferenceWallets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wallets.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Wallet wallet = keyNode.getValue(Wallet.class);
                    wallets.add(wallet);
                }
                dataStatus.DataIsLoaded(wallets , keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readSections(final DataStatus1 dataStatus) {

        mReferenceSections.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                section.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Sections sections = keyNode.getValue(Sections.class);
                    section.add(sections);
                }
                dataStatus.DataIsLoaded(section , keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void addWallet(Wallet wallet,final DataStatus dataStatus) {
        String key = mReferenceWallets.push().getKey();
        mReferenceWallets.child(key).setValue(wallet)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
    // that's for the update button
    public void updateWallet(String key , Wallet wallet , final DataStatus dataStatus){
        mReferenceWallets.child(key).setValue(wallet)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });

    }
    //this is for deleting
    public void deletWallet(String key , final DataStatus dataStatus) {
        // if the value of a child is a null then the child well be deleted
        mReferenceWallets.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();

                    }
                });

    }
}
