package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    RandomGenerator rg = new RandomGenerator();
    Intent service;
    EditText display;
    //Button stop_butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service = new Intent(this, RandomGenerator.class);
        display =  findViewById(R.id.display_char);

        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
                    char x = intent.getCharExtra("data", 'A');
                    String y = Character.toString(x);

                    display.setText(y);
                    Log.i(" test", "see letter: " + x);
                } catch(Exception ex){
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }


            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.broadcast.RANDOM_BROADCAST");
        registerReceiver(br, filter);


    }

    public void RNG_Start(View view){
        //stop_butt =  (Button) findViewById(R.id.stop_service);
        if(!(rg.thread_running)) {
            //rg.onCreate();
            //service = new Intent(getApplicationContext(), RandomGenerator.class);
            startService(service);
            rg.thread_running = true;
            //stop_butt.setEnabled(true);
            Toast.makeText(getApplicationContext(), "Random Generator Started!", Toast.LENGTH_SHORT).show();
        }

    }

    public void RNG_Stop(View view){
        if(rg.thread_running) {
            //rg.onDestroy();
            //service = new Intent(getApplicationContext(), RandomGenerator.class);
            stopService(service);
            rg.thread_running = false;
            display.setText("");

            Toast.makeText(getApplicationContext(), "Random Generator Stopped!", Toast.LENGTH_SHORT).show();
        }

    }
}
