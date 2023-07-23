package com.minhpt.and102_assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class TrangChu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
//            navigationView.setCheckedItem(R.id.manage);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.manage) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuanLy()).commit();
        }
//        else if (item.getItemId() == R.id.introduce) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Bookmark()).commit();
//        } else if (item.getItemId() == R.id.setting) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Setting()).commit();
//        } else if (item.getItemId() == R.id.logout) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Logout()).commit();
//        }
        drawerLayout.close();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}