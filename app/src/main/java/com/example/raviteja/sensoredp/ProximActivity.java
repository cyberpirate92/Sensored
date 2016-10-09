package com.example.raviteja.sensoredp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class ProximActivity extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor mProximity;
    private TextView distanceView, maxDistanceView, minDistanceView;
    private ImageView distanceColorView;
    private final String MAX_DIST = "Maximum: ";
    private final String MIN_DIST = "Minimum: ";
    private final String CUR_DIST = "Current: ";
    private final String DIST_UNIT = " cm";
    private float maxDistance, minDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxim);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mProximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(mProximity == null) {
            Toast.makeText(this, "Sorry, your device does not have a proximity sensor", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        maxDistance = -1;
        minDistance = 1<<29;
        distanceView = (TextView) findViewById(R.id.textView3);
        maxDistanceView = (TextView) findViewById(R.id.textView4);
        minDistanceView = (TextView) findViewById(R.id.textView5);
        distanceColorView = (ImageView) findViewById(R.id.imageView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentDistance = event.values[0];
        distanceView.setText(CUR_DIST+String.format(Locale.ENGLISH,"%.2f",currentDistance)+DIST_UNIT);
        if(maxDistance < currentDistance) {
            maxDistance = currentDistance;
            maxDistanceView.setText(MAX_DIST+String.format(Locale.ENGLISH,"%.2f", maxDistance)+DIST_UNIT);
        }
        if(minDistance > currentDistance) {
            minDistance = currentDistance;
            minDistanceView.setText(MIN_DIST+String.format(Locale.ENGLISH,"%.2f", minDistance)+DIST_UNIT);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
