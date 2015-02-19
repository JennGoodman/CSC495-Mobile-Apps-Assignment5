package csc495s15.assignment5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> deviceSensors = sm.getSensorList(Sensor.TYPE_ALL);

        ArrayList<String> deviceSensorNames = new ArrayList<String>();

        for (Sensor s:deviceSensors) {
            deviceSensorNames.add(s.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, deviceSensorNames);

        ListView lv = (ListView) findViewById(R.id.sensorList);
        lv.setAdapter(adapter);
    }

    public void onClick(View v) {
        if (v==findViewById(R.id.buttonReadings)) {
            startActivity(new Intent(this, SensorReadings.class));
        } else if(v==findViewById(R.id.buttonAction)) {
            startActivity(new Intent(this, SensorAction.class));
        } else {
            //do nothing.
        }
    }
}
