package com.example.supermercado_el_economico.Delivery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.supermercado_el_economico.BaseActivity;
import com.example.supermercado_el_economico.PedidosEnProcesoFragment;
import com.example.supermercado_el_economico.R;
import com.google.android.material.tabs.TabLayout;



public class HomeRepartidor extends BaseActivity {


    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    PedidosEnProcesoFragment pedidosEnProcesoFragment;

    FrameLayout viewPager2;
    TabLayout tabLayout;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_repartidor);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_home_repartidor, null, false);
        drawer.addView(contentView, 0);

        bottomNavigationView.getMenu().findItem(R.id.about).setChecked(true);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        pedidosNuevos();
                        break;
                    case 1:
                        pedidosEnProceso();
                        break;
                    case 2:
                        pedidosEntregados();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }


    public void pedidosNuevos()
    {



    }

    public void pedidosEntregados()
    {


    }

    public void pedidosEnProceso()
    {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        pedidosEnProcesoFragment = new PedidosEnProcesoFragment(this, fragmentManager);

        fragmentTransaction.replace(R.id.view_pager, pedidosEnProcesoFragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_menu_second) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}