package com.example.aaa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class pleaseAdapter extends RecyclerView.Adapter{
    List<Wallet> mWalletList;
    List<Sections> mSectionList;
    List<String> mKey;
    Context x;

    public pleaseAdapter(List<Wallet> mWalletList, List<String> mKey, Context x) {
        this.mWalletList = mWalletList;
        this.mKey = mKey;
        this.x = x;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sections_recycler_view, parent, false);
       ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).SectionsNames.setText(mWalletList.get(position).getSectionsNames());
        ((ViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(x, CardsPage.class);
                x.startActivity(j);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mWalletList.size();
    }
}
class ViewHolder extends RecyclerView.ViewHolder {
    public TextView SectionsNames;
    public TextView wallet_name;
    public View view;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        SectionsNames = itemView.findViewById(R.id.textView34);
    }
}

