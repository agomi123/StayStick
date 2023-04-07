package com.example.staystick;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    ArrayList<ContactModal> arrayList;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
   // PersonAdapter personAdapter;
    //RecyclerView.LayoutManager mLayoutManager;
   // RecyclerView recyclerView;
    public ContactAdapter(Context context, ArrayList<ContactModal>arrayList) {
        this.context=context;
        this.arrayList = arrayList;
       //  mLayoutManager = new LinearLayoutManager(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.letter_item_view, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       ContactModal parentItem = arrayList.get(position);
       // Log.d("List",parentItem.st+"\n");
        holder.charac.setText(parentItem.getSt()+"");
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setInitialPrefetchItemCount(parentItem.getChildItemList().size());
        PersonAdapter childItemAdapter = new PersonAdapter(context,parentItem.getChildItemList());
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(childItemAdapter);
        holder.recyclerView.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView charac;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            charac=itemView.findViewById(R.id.letter);
            recyclerView=itemView.findViewById(R.id.recyclerview);

        }
    }

}
