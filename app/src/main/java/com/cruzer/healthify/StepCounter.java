package com.cruzer.healthify;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounter extends AppCompatActivity implements SensorEventListener {


    SharedPreferences mPrefs;
    SharedPreferences.Editor editor;
    int stepsNum;

    TextView steps;

    SensorManager sensorManager;

    boolean start;

    boolean running = false;



    public void startRun(View view) {
        start = true;

        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }

    public void pauseRun(View view) {

        start = false;

        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();

    }

    public void resetRun(View view) {

        start = false;

        stepsNum = 0;
        steps.setText("0 steps.");
        Toast.makeText(this, "Steps Reset", Toast.LENGTH_SHORT).show();
    }

    public void endRun(View view){
        editor.putInt("STEP_NUMBER", stepsNum);
        editor.apply();
        setResult(RESULT_OK);
        finish();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        steps = (TextView) findViewById(R.id.steps);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mPrefs = getSharedPreferences("com.lighttest.sharedpreferences", MODE_PRIVATE);
        editor = mPrefs.edit();
        // String fName = mPrefs.getString("FIRST_NAME", "noData");


    }

    @Override
    protected void onResume() {

        // while(start == "True") {

        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (countSensor != null) {
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not available.", Toast.LENGTH_SHORT).show();
        }

        //  }
    }

    @Override
    protected void onPause() {
        super.onPause();
        running = false;
        // sensorManager.unregisterListener(this);  // if you unregister the hardware will stop detecting steps
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(running){
            // steps.setText(String.valueOf(event.values[0]) + " steps.");
            if(start) {
                stepsNum ++;
                steps.setText(String.valueOf(stepsNum) + " steps.");
                editor.putInt("STEP_NUMBER",stepsNum);
                editor.apply();
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
