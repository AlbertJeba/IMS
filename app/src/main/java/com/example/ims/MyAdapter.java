package com.example.ims;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList) {

        this.mList = mList;
        this.context = context;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item2, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
       // holder.c1.setText(model.getC1());
        // holder.n1.setText(model.getN1());
        holder.name.setText(model.getName());
       holder.role.setText(model.getRole());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView c1, n1, name, role;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // c1= itemView.findViewById(R.id.category_name);
           // n1 = itemView.findViewById(R.id.Nos);
            name = itemView.findViewById(R.id.ename);
            role = itemView.findViewById(R.id.role1);
        }
    }
}