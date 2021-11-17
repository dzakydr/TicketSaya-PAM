package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashAct  extends AppCompatActivity {

    Animation app_splash , btt;
    ImageView app_logo;
    TextView app_tagline;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //load animation nya
        app_splash = AnimationUtils.loadAnimation(this , R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this , R.anim.btt);

        //load element gambar nya
        app_logo = findViewById(R.id.app_logo);
        app_tagline = findViewById(R.id.app_tagline);

        //jalanin animation nya
        app_logo.startAnimation(app_splash);
        app_tagline.startAnimation(btt);

        getUsernameLocal();


    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

        if(username_key_new.isEmpty()) {
            //setting timer 3 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //merubah activity ke activity laen
                    Intent gogetstarted = new Intent(SplashAct.this , GetStartedAct.class);
                    startActivity(gogetstarted);
                    finish();
                }
            }, 3000);
        }

        else {
            //setting timer 3 detik
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //merubah activity ke activity laen
                    Intent gogethome = new Intent(SplashAct.this , HomeAct.class);
                    startActivity(gogethome);
                    finish();
                }
            }, 3000);
        }
    }
}
