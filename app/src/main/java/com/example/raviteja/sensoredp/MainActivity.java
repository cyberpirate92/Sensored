package com.example.raviteja.sensoredp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startProximActivity(View view) {
        navigateTo(ProximActivity.class);
    }

    public void startGyroActivity(View view) {
        navigateTo(GyroActivity.class);
    }

    public void startAcceleroActivity(View view) {
        navigateTo(AcceleroActivity.class);
    }

    private void navigateTo(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}
