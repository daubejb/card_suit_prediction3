package com.example.root.cardsuitprediction3;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseASuitActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    TextView suitView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_asuit);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        suitView = (TextView) findViewById(R.id.suittextview);
        suitView.setVisibility(View.INVISIBLE);

        Button resetButton = (Button) findViewById(R.id.resetbutton);

        resetButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        suitView.setText("YOU WILL CHOOSE CLUBS");
                        suitView.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] > 0.5) {
            suitView.setVisibility(View.VISIBLE);

        }
    }

}

