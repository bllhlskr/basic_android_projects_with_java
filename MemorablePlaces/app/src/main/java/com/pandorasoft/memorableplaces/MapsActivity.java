package com.pandorasoft.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

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
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    LocationManager locationManager;
    LocationListener locationListener;
    private GoogleMap mMap;
    Marker myMarker;


    public void centerMapOnLocation(Location location,String title){
        LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,8));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastKnownLoation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            centerMapOnLocation(lastKnownLoation,"Your Location");
        }
    }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        Intent intent = getIntent();
       if(intent.getIntExtra("PlaceNumber",4535)==0){

           locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
           locationListener = new LocationListener() {
               @Override
               public void onLocationChanged(Location location) {
                   centerMapOnLocation(location,"Youre Location");
               }

               @Override
               public void onStatusChanged(String provider, int status, Bundle extras) {

               }

               @Override
               public void onProviderEnabled(String provider) {

               }

               @Override
               public void onProviderDisabled(String provider) {

               }
           };
           if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

               locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
           Location lastKnownLoation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
           centerMapOnLocation(lastKnownLoation,"Your Location");

           }else{
               ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);

           }
       }Location placeLocation = new Location(LocationManager.GPS_PROVIDER);
    }
    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";
        try{
            List<Address> listadress = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(listadress != null && listadress.size() >0){
                if(listadress.get(0).getThoroughfare() != null){
                    if(listadress.get(0).getSubThoroughfare() != null){

                        address += listadress.get(0).getSubThoroughfare() + " ";
                    }
                    address += listadress.get(0).getThoroughfare();

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        mMap.addMarker(new MarkerOptions().position(latLng).title("Your new memorable place").snippet("haliskara"));

        MainActivity.places.add(address);
       MainActivity.arrayAdapter.notifyDataSetChanged();

    }


    }

