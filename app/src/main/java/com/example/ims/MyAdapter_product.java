package com.example.ims;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class MyAdapter_product extends FirebaseRecyclerAdapter<Model, MyAdapter_product.myviewholder> {
    public MyAdapter_product(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final Model model) {
        holder.mname.setText(model.getMname());
        holder.model.setText(model.getModel());
        holder.brand.setText(model.getBrand());
        holder.category.setText(model.getC1());
        holder.Quantity.setText(model.getQuantity());
        holder.sold.setText(model.getSold());
        holder.price.setText(model.getPrice());


        holder.edit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.mname.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true, 1500)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText P_name = myview.findViewById(R.id.d_name);
                final EditText C_name = myview.findViewById(R.id.d_cname);
                final EditText Brand = myview.findViewById(R.id.d_brand);
                final EditText Model = myview.findViewById(R.id.d_model);
                final EditText Quantity = myview.findViewById(R.id.d_quantity);
                final EditText Sold = myview.findViewById(R.id.d_sold);
                final EditText Price = myview.findViewById(R.id.d_price);

                Button submit = myview.findViewById(R.id.d_update);


                P_name.setText(model.getMname());
                C_name.setText(model.getC1());
                Brand.setText(model.getBrand());
                Model.setText(model.getModel());
                Quantity.setText(model.getQuantity());
                Sold.setText(model.getSold());
                Price.setText(model.getPrice());

int q=Integer.parseInt(String.valueOf(Quantity));
                dialogPlus.show();


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Map<String, Object> map = new HashMap<>();
                        map.put("mname", P_name.getText().toString());
                        map.put("c1", C_name.getText().toString());
                        map.put("brand", Brand.getText().toString());
                        map.put("model", Model.getText().toString());
                        map.put("quantity", Quantity.getText().toString());
                        map.put("sold", Sold.getText().toString());
                        map.put("price", Price.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {

                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(view.getContext(), "Product updated successfully!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(view.getContext(), "Product Not Updated!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });


                        }


                });

            }
        });
        holder.delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.mname.getContext());
                builder.setTitle("Delete Date");
                builder.setMessage("Do you want to delete this product?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Category")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });



    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder {
        TextView mname, Quantity, model, brand, category, sold,price;

        ImageView edit_data,delete_data;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            mname = itemView.findViewById(R.id.m_name);
            model = itemView.findViewById(R.id.model_);
            brand = itemView.findViewById(R.id.brand_name);
            Quantity = itemView.findViewById(R.id.quantity_);
            category = itemView.findViewById(R.id.cat_name);


            delete_data = itemView.findViewById(R.id.delete_icon);

            sold = itemView.findViewById(R.id.sold);
            price=itemView.findViewById(R.id.price_);

            edit_data = itemView.findViewById(R.id.edit_icon);

        }
    }
}
