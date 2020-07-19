package com.example.aaa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config {

    private Context mContext;
    private Context fContext;
    private DatabaseReference mReferenceWallets;
    private WalletsAdapter mWalletsAdapter;
    private SectionsAdapter mSectionsAdapter;
    private pleaseAdapter pleaseAdapter;

    public void setConfig(RecyclerView recyclerView , Context context
            , List<Wallet> wallets , List<String> keys) {
        mContext = context;
        mWalletsAdapter = new WalletsAdapter(wallets , keys);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        recyclerView.setAdapter(mWalletsAdapter);

    }

    public void setConfig1(RecyclerView recyclerView , Context context
            , List<Wallet> wallets , List<String> mKeys) {

        fContext = context;
        pleaseAdapter = new pleaseAdapter(wallets , mKeys , context);
        recyclerView.setHasFixedSize(true);
        RecyclerView_Margin itemDecorator1 = new RecyclerView_Margin(0);
        recyclerView.addItemDecoration(itemDecorator1);
        recyclerView.setLayoutManager(new GridLayoutManager(fContext , 2));
        recyclerView.setAdapter(pleaseAdapter);


    }
    public class WalletItemView extends RecyclerView.ViewHolder {
        private TextView wallet_name;

        private String key;

        public WalletItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.recyclerview_walletspage, parent , false));
            wallet_name = (TextView) itemView.findViewById(R.id.wallet_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent Sections = new Intent(mContext , SectionsPage.class);
                    mContext.startActivity(Sections);
                    Query query = FirebaseDatabase.getInstance().getReference("Wallets").orderByChild("sectionNames");
                    query.addValueEventListener(valueEventListener);
                }
            });

        }

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Wallet wallet = keyNode.getValue(Wallet.class);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };

        public void bind(Wallet wallet , String key) {
            wallet_name.setText(wallet.getName());

            this.key = key;
        }
    }

    public class WalletItemView1 extends RecyclerView.ViewHolder {
        private TextView Section;

        private String key;

        public WalletItemView1(ViewGroup parent) {
            super(LayoutInflater.from(fContext)
                    .inflate(R.layout.sections_recycler_view, parent , false));
            Section = (TextView) itemView.findViewById(R.id.textView34);


        }



        public void bind(Wallet wallet , String key) {
            Section.setText(wallet.getSectionsNames());

            this.key = key;
        }
    }


    class WalletsAdapter extends RecyclerView.Adapter<WalletItemView> {
        private List<Wallet> mWalletList;
        private List<String> mKeys;


        public WalletsAdapter(List<Wallet> mwalletList, List<String> mkeys) {
            this.mWalletList = mwalletList;
            this.mKeys = mkeys;
        }

        @NonNull
        @Override
        public WalletItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new WalletItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull WalletItemView holder, int position) {
            holder.bind(mWalletList.get(position) , mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mWalletList.size();
        }
    }



    class SectionsAdapter extends RecyclerView.Adapter<WalletItemView1> {
        private List<Wallet> mWalletList;
        private List<String> mKeys;

        public SectionsAdapter(List<Wallet> mwalletList, List<String> mkeys) {
            this.mWalletList = mwalletList;
            this.mKeys = mkeys;
        }


        @NonNull
        @Override
        public WalletItemView1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new WalletItemView1(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull WalletItemView1 holder, int position) {
            holder.bind(mWalletList.get(position) , mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mWalletList.size();
        }
    }
}