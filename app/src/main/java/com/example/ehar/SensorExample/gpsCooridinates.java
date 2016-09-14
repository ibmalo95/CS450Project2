package com.example.ehar.SensorExample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.Observable;

/**
 * Created by Ina on 9/12/16.
 */
public class gpsCooridinates
        extends Observable {

    LocationManager locationManager;
    LocationListener locationListener;
    double lat = 0;
    double lon = 0;

    public gpsCooridinates(Activity act) {
        final Activity activity = act;
        locationManager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            // Called when a new location is found by the network location provider.
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                double [] coordinates = {lat, lon};
                setChanged();
                notifyObservers(coordinates);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                if (status == 2)
                    Toast.makeText(activity, "Provider Available", Toast.LENGTH_SHORT).show();
                else if (status == 1)
                    Toast.makeText(activity, "Provider Temporarily Unavailable", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(activity, "Provider Out of Service", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ContextCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

}
