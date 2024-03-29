package com.example.ims;

import android.os.Bundle;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewAnimator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import java.util.ArrayList;
import java.util.HashMap;

public class cuser extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference root = db.getReference().child("login");
    MyAdapter_user adapter1;
    ArrayList<Model> list;
    ImageButton arrow;
    Button add;
    LinearLayout hiddenView;
    CardView cardView;
    Spinner spinner;
    EditText username, email_id, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuser);
        add = findViewById(R.id.add_user);
        username = findViewById(R.id.username);
        email_id = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        recyclerView = findViewById(R.id.rcuser);
        spinner = findViewById(R.id.spinner1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardView = findViewById(R.id.base_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view_cuser);
        list = new ArrayList<>();
        adapter1 = new MyAdapter_user(this, list);


        recyclerView.setAdapter(adapter1);

        //spinner dropdown
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"admin", "employee"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        // There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

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



        //add user
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String email = email_id.getText().toString();
                String pass = password.getText().toString();
                String role = spinner.getSelectedItem().toString();

                //validation
                if (TextUtils.isEmpty(user)) {

                    username.setError("User Name is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {

                    email_id.setError("Email ID is required");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {

                    password.setError("Password is required");
                    return;
                }
                //validation-end

                HashMap<String, String> userMap = new HashMap<>();

                userMap.put("username", user);
                userMap.put("email", email);
                userMap.put("password", pass);
                userMap.put("role", role);

                root.child(user).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(cuser.this, "User Created", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        //display user
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}