package com.example.servicehub;

import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class maps_first extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps_first);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        String startLocation = getIntent().getStringExtra("startLocation");
        if (startLocation != null && !startLocation.isEmpty()) {

            LatLng location = new LatLng(-34, 151);
            mMap.addMarker(new MarkerOptions().position(location).title("Start Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
        } else {
            Toast.makeText(this, "Start location not provided", Toast.LENGTH_SHORT).show();
        }
    }
}
