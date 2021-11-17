package com.example.ims;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Product extends AppCompatActivity {
    RecyclerView recview;
    MyAdapter_product adapter;
    ImageView arrow;
    CardView cardView;
    LinearLayout hiddenView;
    EditText cat_name, pro_name, br_name, model_name, qua, price;
    Button add_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //add product
        arrow = findViewById(R.id.arrow_button);
        cardView = findViewById(R.id.product_page);
        hiddenView = findViewById(R.id.hidden_view1);

        recview = (RecyclerView) findViewById(R.id.product);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Model.class)
                        .build();

        adapter = new MyAdapter_product(options);
        recview.setAdapter(adapter);

        //expand
        arrow.setOnClickListener(view -> {

            // If the CardView is already expanded, set its visibility
            //  to gone and change the expand less icon to expand more.
            if (hiddenView.getVisibility() == View.VISIBLE) {

                // The transition of the hiddenView is carried out
                //  by the TransitionManager class.
                // Here we use an object of the AutoTransition
                // Class to create a default transition.
                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                hiddenView.setVisibility(View.GONE);
                arrow.setImageResource(R.drawable.expand_one);
            }

            // If the CardView is not expanded, set its visibility
            // to visible and change the expand more icon to expand less.
            else {

                TransitionManager.beginDelayedTransition(cardView,
                        new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.upexpand);
            }
        });
        cat_name = (EditText) findViewById(R.id.a_category);
        pro_name = (EditText) findViewById(R.id.a_product);
        br_name = (EditText) findViewById(R.id.a_brand);
        model_name = (EditText) findViewById(R.id.a_model);
        qua = (EditText) findViewById(R.id.a_quantity);
        price = (EditText) findViewById(R.id.a_price);


        add_user = (Button) findViewById(R.id.add_user);
        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void processinsert() {
        String cat = cat_name.getText().toString();
        String pro = pro_name.getText().toString();
        String br = br_name.getText().toString();
        String mo = model_name.getText().toString();
        String qu = qua.getText().toString();
        String pr = price.getText().toString();

        if (TextUtils.isEmpty(cat)) {

            cat_name.setError("Category Name is required");
            return;
        }

        if (TextUtils.isEmpty(pro)) {

            pro_name.setError("Product Name is required");
            return;
        }

        if (TextUtils.isEmpty(br)) {

            br_name.setError("Brand Name is required");
            return;
        }

        if (TextUtils.isEmpty(mo)) {

            model_name.setError("Model Name is required");
            return;
        }

        if (TextUtils.isEmpty(qu)) {

            qua.setError("Quantity is required");
            return;
        }

        if (TextUtils.isEmpty(pr)) {

            price.setError("Quantity is required");
            return;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("c1", cat_name.getText().toString());
        map.put("mname", pro_name.getText().toString());
        map.put("brand", br_name.getText().toString());
        map.put("model", model_name.getText().toString());
        map.put("quantity", qua.getText().toString());
        map.put("price", price.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("Category").child(pro)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        cat_name.setText("");
                        pro_name.setText("");
                        br_name.setText("");
                        model_name.setText("");
                        qua.setText("");
                        price.setText("");

                        Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Could not insert", Toast.LENGTH_LONG).show();
                    }
                });

    }


}