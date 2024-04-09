package com.example.supermercado_el_economico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class AddressLocation extends Fragment {

    private FragmentManager _fragmentManager;
    public AddressLocation(FragmentManager fragmentManager) {
        _fragmentManager = fragmentManager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){

        View rootLayout = inflater.inflate(R.layout.activity_address_location, container, false);

        Toolbar toolbar = rootLayout.findViewById(R.id.toolbar);
        AppCompatActivity appCompatActivity = (AppCompatActivity) requireActivity();
        appCompatActivity.setSupportActionBar(toolbar);
        ActionBar actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Aceptar orden");

            toolbar.setNavigationOnClickListener(v -> {
                _fragmentManager.popBackStack();
            });
        }

        return rootLayout;
    }


}