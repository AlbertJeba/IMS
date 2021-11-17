package com.example.ims;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class login_page extends AppCompatActivity {

    EditText name, password;
    Button loginbutton;
    // ProgressBar progressBar;
    Switch active;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //  progressBar = findViewById(R.id.progressBar);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginBtn);
        active = findViewById(R.id.active);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = name.getText().toString();
                        String input2 = password.getText().toString();

                        if (TextUtils.isEmpty(input1)) {

                            name.setError("User Name is required");
                            return;
                        }
                        if (TextUtils.isEmpty(input2)) {

                            password.setError("Password is required");
                            return;
                        }
                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(login_page.this, true);
                                        preferences.setData_role(login_page.this, "admin");
                                        startActivity(new Intent(login_page.this, Dashboard.class));
                                        finish();
                                    } else if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("employee")) {
                                        preferences.setDataLogin(login_page.this, true);
                                        preferences.setData_role(login_page.this, "employee");
                                        startActivity(new Intent(login_page.this, Employee_dashboard.class));
                                        finish();
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(login_page.this, false);
                                        startActivity(new Intent(login_page.this, Dashboard.class));
                                        finish();

                                    } else if (dataSnapshot.child(input1).child("role").getValue(String.class).equals("employee")) {
                                        preferences.setDataLogin(login_page.this, false);
                                        startActivity(new Intent(login_page.this, Employee_dashboard.class));
                                        finish();
                                    }
                                }

                            } else {
                                Toast.makeText(login_page.this, "Wrong password", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login_page.this, "Data not registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            if (preferences.getData_role(this).equals("admin")) {
                startActivity(new Intent(this, Dashboard.class));
            } else {
                startActivity(new Intent(this, Employee_dashboard.class));
            }
            finish();
        }
    }
}