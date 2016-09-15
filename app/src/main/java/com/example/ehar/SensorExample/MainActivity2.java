package com.example.ehar.SensorExample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;


public class MainActivity2
        extends AppCompatActivity
        implements Observer {

    // Permissions constant
    public final int PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    // Keys for orientation change
    public final String X = "X";
    public final String Y = "Y";
    public final String Z = "Z";
    public final String LAT = "LAT";
    public final String LON = "LON";

    // TextViews
    private TextView accel_x_view = null;
    private TextView accel_y_view = null;
    private TextView accel_z_view = null;
    private TextView gps_lat_view = null;
    private TextView gps_lon_view = null;

    //Observables
    private Observable accel;
    private Observable gpsCo;

    @Override
    public void update(Observable observable, Object o) {
        // update gps views
        if (observable == gpsCo) {
            double [] coordinates = (double []) o;
            gps_lat_view.setText(Double.toString(coordinates[0]));
            gps_lon_view.setText(Double.toString(coordinates[1]));

        }
        // update accelerometer views
        else {
            float [] values = (float []) o;
            accel_x_view.setText(Float.toString(values[0]));
            accel_y_view.setText(Float.toString(values[1]));
            accel_z_view.setText(Float.toString(values[2]));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get gps latitude and longitude
        gps_lat_view = (TextView) findViewById(R.id.gps_lat);
        gps_lon_view = (TextView) findViewById(R.id.gps_lon);
        this.getPermissions();
        this.gpsCo = new gpsCoordinates(this);
        this.gpsCo.addObserver(this);

        // Get accelerometer values
        accel_x_view = (TextView) findViewById(R.id.accel_x);
        accel_y_view = (TextView) findViewById(R.id.accel_y);
        accel_z_view = (TextView) findViewById(R.id.accel_z);
        this.accel = new AccelerometerHandler(500, this);
        this.accel.addObserver(this);
    }

    public void getPermissions() {
        // Check if app has access to FINE_LOCATION
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // if user denys continue; only the accelerometer values will be visible
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, R.string.require_permission, Toast.LENGTH_SHORT).show();

            } else {
                // user accepts
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // set the views
        accel_x_view.setText(getPreferences(MODE_PRIVATE).getString(X, "0"));
        accel_y_view.setText(getPreferences(MODE_PRIVATE).getString(Y, "0"));
        accel_z_view.setText(getPreferences(MODE_PRIVATE).getString(Z, "0"));
        gps_lat_view.setText(getPreferences(MODE_PRIVATE).getString(LAT, "0"));
        gps_lon_view.setText(getPreferences(MODE_PRIVATE).getString(LON, "0"));

    }

    @Override
    protected void onPause() {
        super.onPause();
        // save the views in case of orientation change
        getPreferences(MODE_PRIVATE).edit().putString(X, accel_x_view.getText().toString()).apply();
        getPreferences(MODE_PRIVATE).edit().putString(Y, accel_y_view.getText().toString()).apply();
        getPreferences(MODE_PRIVATE).edit().putString(Z, accel_z_view.getText().toString()).apply();
        getPreferences(MODE_PRIVATE).edit().putString(LAT, gps_lat_view.getText().toString()).apply();
        getPreferences(MODE_PRIVATE).edit().putString(LON, gps_lon_view.getText().toString()).apply();
    }
}
