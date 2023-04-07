package com.example.staystick;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    List<Person> arrayList;
    Context context;
    public PersonAdapter(Context context, List<Person> arrayList) {
        this.context=context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_view, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
    // Person person=arrayList.get(position);
    // holder.name.setText(person.getName());
//     if(person.imgphoto.equals("") || person.imgphoto.equals("null")){
//         Glide.with(context).load(R.drawable.ic_baseline_person).into(holder.img);
//     }
//     else{
//         Glide.with(context).load(Uri.parse(person.getImgphoto())).into(holder.img);
//     }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CircleImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.person_name);
            img=itemView.findViewById(R.id.person_img);
        }
    }

}
