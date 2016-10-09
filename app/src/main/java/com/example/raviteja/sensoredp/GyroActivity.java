package com.example.raviteja.sensoredp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class GyroActivity extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor mGyroscope;
    private TextView xAxisTextView, yAxisTextView, zAxisTextView;
    private float xAxis, yAxis, zAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mGyroscope = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyroscope == null) {
            Toast.makeText(this, "Sorry, your device does not have a Gyrometer.", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        xAxisTextView = (TextView) findViewById(R.id.textView9);
        yAxisTextView = (TextView) findViewById(R.id.textView10);
        zAxisTextView = (TextView) findViewById(R.id.textView11);
    }


    public void setxAxis(float xAxis) {
        this.xAxis = xAxis;
        xAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.xAxis));
    }

    public void setyAxis(float yAxis) {
        this.yAxis = yAxis;
        yAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.yAxis));
    }

    public void setzAxis(float zAxis) {
        this.zAxis = zAxis;
        zAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.zAxis));
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        this.setxAxis(event.values[0]);
        this.setyAxis(event.values[1]);
        this.setzAxis(event.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
