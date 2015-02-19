package csc495s15.assignment5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SensorAction extends ActionBarActivity implements SensorEventListener{

    private SensorManager sm;
    private Sensor accel;
    private float[] lastMove = {0,0,0};
    float movementTolerance = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_action);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this, accel);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor == accel) {
            float dx = event.values[0];
            float dy = event.values[1];
            float dz = event.values[2];

            if (lastMove[0] > 0 || lastMove[1] > 0 || lastMove[2] > 0){

                TextView shaken = (TextView) findViewById(R.id.textViewShake);

                if (Math.abs(lastMove[0] - dx) > movementTolerance) {shaken.setText("Shaken!");};
                if (Math.abs(lastMove[1] - dy) > movementTolerance) {shaken.setText("Shaken!");};
                if (Math.abs(lastMove[2] - dz) > movementTolerance) {shaken.setText("Shaken!");};
            }
            lastMove[0] = dx;
            lastMove[1] = dy;
            lastMove[2] = dz;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { /* do nothing */ }

    public void onClick(View v) {
        finish();
    }
}
