package com.example.ehar.SensorExample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Observable;
import java.util.Observer;


public class MainActivity2
        extends AppCompatActivity
        implements Observer {

    public final int PERMISSIONS_ACCESS_FINE_LOCATION = 1;

    // textviews
    private TextView accel_x_view = null;
    private TextView accel_y_view = null;
    private TextView accel_z_view = null;
    private TextView gps_lat_view = null;
    private TextView gps_lon_view = null;
    private Observable accel;
    private Observable gpsCo;

    @Override
    public void update(Observable observable, Object o) {
        if (observable == gpsCo) {
            double [] coordinates = (double []) o;
            gps_lat_view.setText(Double.toString(coordinates[0]));
            gps_lon_view.setText(Double.toString(coordinates[1]));

        }
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

        gps_lat_view = (TextView) findViewById(R.id.gps_lat);
        gps_lon_view = (TextView) findViewById(R.id.gps_lon);
        this.getPermissions();
        this.gpsCo = new gpsCooridinates(this);
        this.gpsCo.addObserver(this);


        accel_x_view = (TextView) findViewById(R.id.accel_x);
        accel_y_view = (TextView) findViewById(R.id.accel_y);
        accel_z_view = (TextView) findViewById(R.id.accel_z);
        this.accel = new AccelerometerHandler(500, this);
        this.accel.addObserver(this);
    }

    public void getPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_ACCESS_FINE_LOCATION);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
