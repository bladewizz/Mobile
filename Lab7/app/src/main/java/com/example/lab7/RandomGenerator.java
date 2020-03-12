package com.example.lab7;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Binder;
import android.util.Log;

import java.util.Random;

import androidx.annotation.Nullable;

public class RandomGenerator extends Service {

    Boolean thread_running = false;
    String tag = "Service";
    private final IBinder binder = new LocalBinder();
    char letter;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(tag, ":Bound");
        return binder;
    }


    @Override
    public void onCreate() {
        Log.i(tag, "Created");
        thread_running = true;
    }
    public boolean onUnbind(Intent intent){
        Log.i(tag, ":Unbound");
        return super.onUnbind(intent);
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
                    letter = n;
                    Log.i("Letter", " " + n);

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
        char r = (char) (rnd.nextInt(26) + 'a');

        return r;

    }

    public class LocalBinder extends Binder{
        RandomGenerator getService(){
            return RandomGenerator.this;
        }
    }

    public char getLetter(){
        return this.letter;
    }
}
