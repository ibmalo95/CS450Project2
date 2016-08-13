package com.example.ehar.SensorExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // textviews
    TextView accel_y_view = null;

    // accelerometer crud
    Sensor accelerometer = null;
    SensorManager sensorManager = null;
    long prev_time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accel_y_view = (TextView) findViewById(R.id.accel_y);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        prev_time = System.currentTimeMillis();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        // don't really need to check this because this is the only sensor registered
        if (sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;

        // must be the accelerometer - number of milliseconds since Midnight, Jan 1, 1970
        // How many milliseconds in a day? 60(60)(24)(1000) = 90,000,000
        long curr_time = System.currentTimeMillis();

        if (curr_time - prev_time > 500) {
            prev_time = curr_time;
            float y = sensorEvent.values[1];
            accel_y_view.setText(Float.toString(y));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
