package com.example.eetime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    private boolean countDown = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On Create code:

        chronometer = findViewById(R.id.chronometer);
    }

    //Start UP
    public void startUpChronometer(View v) {

        if (!running) {

            //Kollar om countDown är true eller false
            if (countDown) {
                chronometer.setBase(SystemClock.elapsedRealtime() + pauseOffset);
            } else if (!countDown) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            }

            //Sätter countDown till false, timer räknar upp
            chronometer.setCountDown(false);

            chronometer.start();

            running = true;
            countDown = false;
        }

    }


    //Pause
    public void pauseChronometer(View v) {

        if (running) {

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }

    //Reset
    public void resetChronometer(View v) {

        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;


    }

    //Start Down
    public void startDownChronometer(View v) {

        if (!running) {
            chronometer.setCountDown(true);

            if(countDown) {
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            }

            if(!countDown){
                chronometer.setBase(SystemClock.elapsedRealtime() + pauseOffset);
            }

            chronometer.start();
            running = true;
            countDown = true;
        }
    }
}