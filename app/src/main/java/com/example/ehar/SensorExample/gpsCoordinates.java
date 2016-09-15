package com.example.ehar.SensorExample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.util.Observable;

/**
 * Created by Ina on 9/12/16.
 */
public class gpsCoordinates
        extends Observable {

    LocationManager locationManager;
    LocationListener locationListener;
    double lat = 0;
    double lon = 0;

    public gpsCoordinates(Activity act) {
        locationManager = (LocationManager) act.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            // Called when a new location is found by the GPS location provider.
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lon = location.getLongitude();
                double [] coordinates = {lat, lon};
                setChanged();
                notifyObservers(coordinates);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        // If user allows access to location start requesting location updates
        if (ContextCompat.checkSelfPermission(act, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }

}
