package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


import android.content.Intent;
import android.os.Bundle;

public class Employee_dashboard extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_dashboard);



    }

    public void add_cat(View view) {
        Intent intent = new Intent(Employee_dashboard.this, Category.class);
        startActivity(intent);
    }
}