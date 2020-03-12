package com.example.lab8;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

import androidx.annotation.Nullable;

public class RandomGenerator extends Service {

    Boolean thread_running = false;
    String tag = "Service";



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(tag, "Created");
        thread_running = true;
    }

    @Override
    public void onDestroy() {
        Log.i(tag, "Destroyed");
        thread_running = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread t = new Thread("New Thread")
        {
            public void run()
            {
                Log.i(tag, "New Thread Created: " + getId());
                while(thread_running)
                {
                    char n = randLetter();
                    Log.i("Letter", " " + n);

                    Intent intent = new Intent();
                    intent.setAction("com.example.broadcast.RANDOM_BROADCAST");
                    intent.putExtra("data", n);
                    sendBroadcast(intent);

                    try
                    {
                        sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        Log.i("Thread broke", "HERE");
                    }

                }

            }
        };
        t.start();
        return START_STICKY;
    }//end onStart


    public char randLetter(){
        Random rnd = new Random();
        char r = (char) (rnd.nextInt(26) + 'A');

        return r;

    }
}
