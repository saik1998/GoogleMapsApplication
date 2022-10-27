package com.firstapp.one_to_one_direction;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.firstapp.one_to_one_direction.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions place1,place2;
    Polyline currentPolyline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        place1=new MarkerOptions().position(new LatLng(17.713114478773722, 83.20215360706237)).title("Vizag");
        place2=new MarkerOptions().position(new LatLng(17.372984519125815, 78.47257736343194)).title("Hyderabad");

        String url=getUrl(place1.getPosition(),place2.getPosition(),"Driviing");


//        new FetchUrl(MapsActivity.this).excute();




    }

    private String getUrl(LatLng position, LatLng position1, String driviing) {
        //starting of route
        String str_origin="origin"+ position.latitude+","+position.longitude;
        //destination of rotue
        String str_destination="origin"+ position1.latitude+","+position1.longitude;

        //string mode
        String drive="Mode"+driviing;

        //Builiding the parameters to the web service

        String parameters=str_origin+"&"+str_destination+"&"+drive;
        //output parameter
        String output="json";
        //Buliding the Url path to web service
        String url="https://maps.googleapis.com.maps/api/directions/"+output+"?"+parameters+"&key"+getString(R.string.your_key);
        return  url;



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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}