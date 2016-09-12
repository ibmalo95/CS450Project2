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

    public final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1;

    // textviews
    private TextView accel_x_view = null;
    private TextView accel_y_view = null;
    private TextView accel_z_view = null;
    private Observable accel;

    @Override
    public void update(Observable observable, Object o) {
        float [] values = (float []) o;
        accel_x_view.setText(Float.toString(values[0]));
        accel_y_view.setText(Float.toString(values[1]));
        accel_z_view.setText(Float.toString(values[2]));
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // Ask the user for permision
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_ACCESS_COARSE_LOCATION);
        }


        accel_x_view = (TextView) findViewById(R.id.accel_x);
        accel_y_view = (TextView) findViewById(R.id.accel_y);
        accel_z_view = (TextView) findViewById(R.id.accel_z);
        this.accel = new AccelerometerHandler(500, this);
        this.accel.addObserver(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {
                    Toast.makeText(this, "Need your location!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
