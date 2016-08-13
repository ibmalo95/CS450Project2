package com.example.ehar.SensorExample;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.Observable;

/**
 * Created by ehar on 8/12/2016.
 */
public class AccelerometerHandler extends Observable implements SensorEventListener {



    @Override
    public void onSensorChanged(SensorEvent event) {

        setChanged();
        notifyObservers(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
