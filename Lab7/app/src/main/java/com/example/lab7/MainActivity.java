package com.example.lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    boolean isBound = false;
    boolean myBound = false;
    TextView letter;
    Intent intent;
    RandomGenerator rg;
    //Button stop_butt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = new Intent(this, RandomGenerator.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onStart(View view){
        //super.onStart();
        if(!isBound){
            bindService(intent, connection, Context.BIND_AUTO_CREATE);
            isBound = true;
        }

    }

    public void onStop(View view){
        //super.onStop();
        if(isBound){
            unbindService(connection);
            isBound = false;
        }

    }

    public void RNG_Start(View view){
        if(!(myBound)) {
            //stop_butt =  (Button) findViewById(R.id.stop_service);
            //rg.onCreate();
            //service = new Intent(getApplicationContext(), RandomGenerator.class);
            startService(intent);
            myBound = true;
            //rg.thread_running = true;
            //stop_butt.setEnabled(true);
        }

    }

    public void RNG_Stop(View view){
        if(myBound) {
            //rg.thread_running = false;
            //rg.onDestroy();
            //service = new Intent(getApplicationContext(), RandomGenerator.class);
            stopService(intent);
            myBound = false;

            Toast.makeText(getApplicationContext(), "Random Generator Stopped!", Toast.LENGTH_SHORT).show();
        }

    }

    public void setLetter(View view){
        letter = findViewById(R.id.CharDisplay);
        if(isBound){
            //rg = new RandomGenerator();
            char n = rg.getLetter();
            letter.setText(Character.toString(n));
        }
        else
        {
            letter.setText("Service Not Bound");
        }
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RandomGenerator.LocalBinder binder = (RandomGenerator.LocalBinder) service;
            rg = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };
}
