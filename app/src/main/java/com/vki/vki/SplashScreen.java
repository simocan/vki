package com.vki.vki;

/**
 * Created by Admin on 20.12.2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends Activity {

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer=new Timer();
        timer.schedule(new MyTask(), 3000);
    }


    class MyTask extends TimerTask{

        @Override
        public void run() {
            Intent intent=new  Intent(SplashScreen.this,vki.class);
            finish();
            startActivity(intent);
        }

    }

}