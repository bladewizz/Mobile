package com.example.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    RandomGenerator rg = new RandomGenerator();
    Intent service;
    //Button stop_butt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RNG_Start(View view){
        //stop_butt =  (Button) findViewById(R.id.stop_service);
        rg.onCreate();
        service = new Intent(getApplicationContext(), RandomGenerator.class);
        startService(service);
        rg.thread_running = true;
        //stop_butt.setEnabled(true);

    }

    public void RNG_Stop(View view){
        rg.thread_running = false;
        rg.onDestroy();
        service = new Intent(getApplicationContext(), RandomGenerator.class);
        stopService(service);

        Toast.makeText(getApplicationContext(), "Random Generator Stopped!", Toast.LENGTH_SHORT).show();

    }
}

