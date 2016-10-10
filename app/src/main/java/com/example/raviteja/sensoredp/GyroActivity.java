package com.example.raviteja.sensoredp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class GyroActivity extends Activity implements SensorEventListener {

    private SensorManager manager;
    private Sensor mGyroscope;
    private TextView xAxisTextView, yAxisTextView, zAxisTextView;
    private TextView xAxisMaxView, yAxisMaxView, zAxisMaxView;
    private float xAxis, yAxis, zAxis;
    private float xMax, yMax, zMax;


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
        xMax = yMax = zMax = 0;
        xAxisTextView = (TextView) findViewById(R.id.textView9);
        yAxisTextView = (TextView) findViewById(R.id.textView10);
        zAxisTextView = (TextView) findViewById(R.id.textView11);
        xAxisMaxView = (TextView) findViewById(R.id.textView14);
        yAxisMaxView = (TextView) findViewById(R.id.textView15);
        zAxisMaxView = (TextView) findViewById(R.id.textView16);
    }


    public void setxAxis(float xAxis) {
        this.xAxis = xAxis;
        xAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.xAxis));
        if(xMax < Math.abs(xAxis)) {
            xMax = Math.abs(xAxis);
            xAxisMaxView.setText(String.format(Locale.ENGLISH, "%.2f", this.xMax));
        }

    }

    public void setyAxis(float yAxis) {
        this.yAxis = yAxis;
        yAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.yAxis));
        if(yMax < Math.abs(yAxis)) {
            yMax = Math.abs(yAxis);
            yAxisMaxView.setText(String.format(Locale.ENGLISH, "%.2f", this.yMax));
        }
    }

    public void setzAxis(float zAxis) {
        this.zAxis = zAxis;
        zAxisTextView.setText(String.format(Locale.ENGLISH, "%.2f", this.zAxis));
        if(zMax < Math.abs(zAxis)) {
            zMax = Math.abs(zAxis);
            zAxisMaxView.setText(String.format(Locale.ENGLISH, "%.2f", this.zMax));
        }
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

    public void resetMaxValues(View view) {
        this.xMax = this.yMax = this.zMax = 0;
    }
}
