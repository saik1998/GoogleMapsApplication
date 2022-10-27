package com.firstapp.googlemapsapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.IntentSender;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.firstapp.googlemapsapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LocationRequest locationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationRequest= LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);

        LocationSettingsRequest.Builder builder=new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true);

        Task<LocationSettingsResponse> locationSettingsResponseTask= LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());
        locationSettingsResponseTask.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response=task.getResult(ApiException.class);
//                    Toast.makeText(MapsActivity.this, "location already enabled", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {
//                    e.printStackTrace();
                    if (e.getStatusCode()== LocationSettingsStatusCodes.RESOLUTION_REQUIRED)
                    {
                        ResolvableApiException resolvableApiException= (ResolvableApiException) e;

                        try {
                            resolvableApiException.startResolutionForResult(MapsActivity.this,101);
                        } catch (IntentSender.SendIntentException ex) {
                            ex.printStackTrace();
                        }

                    }
                    else if (e.getStatusCode()==LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE)
                    {
                        Toast.makeText(MapsActivity.this, "Setting not available", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        LatLng hyderabad = new LatLng(17.45760488304606, 78.44451563941777);
//        LatLng dmart = new LatLng(17.446059824240884, 78.459536009104);

        LatLng ammerpet = new LatLng(17.436773018137607, 78.4441205897227);



        // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(ammerpet).snippet("Ammerpet").title("maitrivanam").draggable(false));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(ammerpet));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ammerpet,15f));


        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);

//        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(@NonNull Marker marker) {
//
//            }
//
//            @Override
//            public void onMarkerDragEnd(@NonNull Marker marker) {
//
//            }
//
//            @Override
//            public void onMarkerDragStart(@NonNull Marker marker) {
//
//            }
//        });



    }

}