package com.example.sovan.android_school.newrecorder_1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sovan.android_school.newrecorder_1.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sovan on 22.09.2014.
 */
public class TimerActivity extends Activity {
    TimerTask mTimerTask;
    final Handler handler = new Handler();
    Timer t = new Timer();
    TextView hTextView;
    TableRow hTableRow;
    Button hButton, hButtonStop;


    private int nCounter = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        hTextView = (TextView)findViewById(R.id.idTextView);
        hButton = (Button)findViewById(R.id.idButton);
        hButton.setOnClickListener(mButtonStartListener);
        hButtonStop = (Button)findViewById(R.id.idButtonStop);
        hButtonStop.setOnClickListener(mButtonStopListener);



    } // end onCreate

    View.OnClickListener mButtonStartListener = new View.OnClickListener() {
        public void onClick(View v) {
            doTimerTask();
        }
    };




    View.OnClickListener mButtonStopListener = new View.OnClickListener() {
        public void onClick(View v) {
            stopTask();

        }
    };


    public void doTimerTask(){
        if (mTimerTask == null) {

            mTimerTask = new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            nCounter++;
                            // update TextView
                            hTextView.setText("Timer: " + nCounter);

                            Log.d("TIMER", "TimerTask run");
                        }
                    });
                }
            };

            // public void schedule (TimerTask task, long delay, long period)
            Date date = new Date();
            date.setTime(date.getTime() + 30000);
            t.schedule(mTimerTask, date, 1000);  //
        }
    }

    public void stopTask(){

        if(mTimerTask!=null){
            hTextView.setText("Timer canceled: " + nCounter);

            Log.d("TIMER", "timer canceled");
            mTimerTask.cancel();

            mTimerTask = null;

        }

    }
}