package com.example.supermercado_el_economico.Shop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.supermercado_el_economico.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapView extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener  {

    private final int FINE_PERMISSION_CODE = 1;
    private GoogleMap myMap;
    double latitude, longitude;
    String contacto;
    Location currentLocation;
    MaterialButton saveButton;
    TextInputEditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        saveButton = findViewById(R.id.saveButton);
        addressInput = findViewById(R.id.addressInput);

        mapFragment.getMapAsync(this);

        boolean isPermissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED;

        if (isPermissionGranted) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1001);
        } else {
            getLocation();
        }

        saveButton.setOnClickListener(v -> {
            String reference = String.valueOf(addressInput.getText());
            boolean isNewCorsEmpty = newLatitude == 0.0 && newLongitude == 0.0;
            LocationData locationData  = (isNewCorsEmpty) ?
                        new LocationData(latitude, longitude, reference) : new LocationData(newLatitude, newLongitude, reference);

        });

    }

    public void setLocation(Location loc) {
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String txtLatitud;
    private String txtLongitud;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private LatLng currentLatLng;
    private Marker currentMarker;
    private void getLocation() {
         locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
         locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                txtLatitud = String.valueOf(location.getLatitude());
                txtLongitud = String.valueOf(location.getLongitude());
                latitude = Float.valueOf(txtLatitud);
                longitude = Float.valueOf(txtLongitud);
                setLocation(location);

                if(markerPositionChanged){
                    currentLatLng = new LatLng(newLatitude, newLongitude);
                }else{
                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                }

                boolean currentMarkIsNotNull = currentMarker != null;
                if(currentMarkIsNotNull) {
                    currentMarker.setPosition(currentLatLng);
                } else {
                    MarkerOptions markerOptions = new MarkerOptions()
                            .position(currentLatLng)
                            .title("Current Location")
                            .draggable(true);
                    currentMarker = myMap.addMarker(markerOptions);
                }

                myMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng));
                myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18));
            }

        };

         boolean wereGpsPermissionGranted = ActivityCompat.checkSelfPermission(MapView.this,
                                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                            ActivityCompat.checkSelfPermission(MapView.this,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        if (wereGpsPermissionGranted) {
            ActivityCompat.requestPermissions(MapView.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }

        boolean isFinelocationDone = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;

        if (isFinelocationDone) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        myMap = googleMap;
        myMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    boolean markerPositionChanged = false;
    double newLatitude = 0.0;
    double newLongitude = 0.0;
    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {

        markerPositionChanged = true;
        currentLatLng = marker.getPosition();
        newLatitude = currentLatLng.latitude;
        newLongitude = currentLatLng.longitude;

    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }
}