package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
    public void c_cat(View view) {
        Intent intent = new Intent(Dashboard.this, Category.class);
        startActivity(intent);
    }


    public void c_user(View view) {
        Intent intent = new Intent(Dashboard.this, cuser.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}