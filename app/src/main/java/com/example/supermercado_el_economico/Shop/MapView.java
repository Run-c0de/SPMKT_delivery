package com.example.supermercado_el_economico.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;




import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;





import com.example.supermercado_el_economico.R;

public class MapView extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    EditText nombreInput, addressInput;
    Button saveButton;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        nombreInput = findViewById(R.id.nombreInput);
        addressInput = findViewById(R.id.addressInput);
        saveButton = findViewById(R.id.saveButton);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);




    }
}