package com.example.ims;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter_user extends RecyclerView.Adapter<MyAdapter_user.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter_user(Context context, ArrayList<Model> mList) {

        this.mList = mList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyAdapter_user.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View b = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new MyViewHolder(b);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter_user.MyViewHolder holder, int position) {
        Model model = mList.get(position);

        holder.username.setText(model.getUsername());
        holder.role.setText(model.getRole());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView username, role;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            username = itemView.findViewById(R.id.username);
            role = itemView.findViewById(R.id.role);
        }
    }
}
