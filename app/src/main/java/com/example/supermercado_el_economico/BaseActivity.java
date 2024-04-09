package com.example.supermercado_el_economico;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.supermercado_el_economico.Delivery.HomeRepartidor;
import com.example.supermercado_el_economico.Shop.CustomerAddress;
import com.example.supermercado_el_economico.Shop.MapView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    public DrawerLayout drawer;
    public NavigationView navigationView;
    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

         bottomNavigationView = findViewById(R.id.bottom_navigation);
        AtomicInteger selectedItem = new AtomicInteger(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.dashboard) {
                startActivity(new Intent(getApplicationContext(), MapView.class));
                overridePendingTransition(0, 0);
            }

            if (id == R.id.about) {
                startActivity(new Intent(getApplicationContext(), HomeRepartidor.class));
                overridePendingTransition(0, 0);
            }

            return true;
        });

    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_activity1) {
            startAnimatedActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
        } else if (id == R.id.nav_activity2) {
            startAnimatedActivity(new Intent(getApplicationContext(), PerformanceProfileActivity.class));
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
