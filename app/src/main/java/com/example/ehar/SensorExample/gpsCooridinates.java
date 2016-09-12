package com.example.ehar.SensorExample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.Observable;

/**
 * Created by Ina on 9/12/16.
 */
public class gpsCooridinates
        extends Observable
        implements SensorEventListener {


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
