package com.pandorasoft.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
LocationManager locationManager;
LocationListener locationListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
locationListener = new LocationListener() {
    @Override
    public void onLocationChanged(Location location) {
updateLocaitoninfo(location);
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

if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
}else{
    locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
    Location lastKnownlocaiton = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

    if(lastKnownlocaiton != null){

    }

}

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
           startListening();
        }
    }

    public void startListening(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
    }

    public void updateLocaitoninfo(Location location){

        TextView latTextView = findViewById(R.id.latTextView);
        TextView lonTextView = findViewById(R.id.lonTextView);
        TextView accuracyTextView = findViewById(R.id.accuracyTextView);
        TextView altitudeTextView = findViewById(R.id.altitudeTextView);
        TextView addressTextView = findViewById(R.id.addressTextView);
        latTextView.setText("latitude : " + location.getLatitude());
        lonTextView.setText("Longtitude : " + location.getLongitude());
        accuracyTextView.setText(" accuracy : " + location.getAccuracy());
        altitudeTextView.setText("altitude : " + location.getAltitude());
        String Address = "could not find address";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<android.location.Address> addresslist = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);

            if(addresslist != null && addresslist.size()> 0){
                Address = " Address :\n";


            if(addresslist.get(0).getThoroughfare()!= null){
                Address += addresslist.get(0).getThoroughfare() + "\n";
            }
                if(addresslist.get(0).getPostalCode()!= null){
                    Address += addresslist.get(0).getPostalCode() + "\n";
                }
                if(addresslist.get(0).getAdminArea()!= null){
                    Address += addresslist.get(0).getAdminArea() + "\n";
                }



            }

        }catch(Exception e){
            e.printStackTrace();
        }
        addressTextView.setText(Address );



    }
}
