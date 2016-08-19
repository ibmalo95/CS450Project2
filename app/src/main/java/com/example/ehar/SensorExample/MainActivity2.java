package com.example.ehar.SensorExample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ehar on 8/15/16.
 */
public class MainActivity2
        extends AppCompatActivity
        implements Observer {

    // textviews
    private TextView accel_y_view = null;
    private Observable accel;

    @Override
    public void update(Observable observable, Object o) {
        float [] values = (float []) o;
        accel_y_view.setText(Float.toString(values[1]));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accel_y_view = (TextView) findViewById(R.id.accel_y);
        this.accel = new AccelerometerHandler(500, this);
        this.accel.addObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
