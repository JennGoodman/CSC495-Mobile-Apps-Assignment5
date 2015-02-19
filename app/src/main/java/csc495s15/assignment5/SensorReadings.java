package csc495s15.assignment5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SensorReadings extends ActionBarActivity implements SensorEventListener {

    private SensorManager sm;
    private Sensor accel;
    private Sensor grav;
    private Sensor light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_readings);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        grav = sm.getDefaultSensor(Sensor.TYPE_GRAVITY);
        light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void onClick(View v) {
        finish();
    }

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, grav, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this, accel);
        sm.unregisterListener(this, grav);
        sm.unregisterListener(this, light);
    }

    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // do nothing.
    }

    public final void onSensorChanged(SensorEvent event) {
        if(event.sensor == accel) {
            float dx = event.values[0];
            float dy = event.values[1];
            float dz = event.values[2];
            TextView tv;

            tv = (TextView) findViewById(R.id.accelXAxis);
            tv.setText(String.valueOf(dx));

            tv = (TextView) findViewById(R.id.accelYAxis);
            tv.setText(String.valueOf(dy));

            tv = (TextView) findViewById(R.id.accelZAxis);
            tv.setText(String.valueOf(dz));
        }

        if(event.sensor == grav) {
            float dx = event.values[0];
            float dy = event.values[1];
            float dz = event.values[2];
            TextView tv;

            tv = (TextView) findViewById(R.id.gravXAxis);
            tv.setText(String.valueOf(dx));

            tv = (TextView) findViewById(R.id.gravYAxis);
            tv.setText(String.valueOf(dy));

            tv = (TextView) findViewById(R.id.gravZAxis);
            tv.setText(String.valueOf(dz));
        }

        if (event.sensor == light) {
            TextView tv;

            tv = (TextView) findViewById(R.id.lightValue);
            tv.setText(String.valueOf(event.values[0]));
        }
    }
}
