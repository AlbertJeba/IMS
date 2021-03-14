package com.example.ims;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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



    // menu inflate
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }

    //logout
    public void logout(MenuItem item) {
        startActivity(new Intent(this,login_page.class));
        preferences.clearData(this);
        finish();
    }
}