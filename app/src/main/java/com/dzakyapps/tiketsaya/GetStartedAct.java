package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GetStartedAct extends AppCompatActivity {

    Button btn_sign_in;
    Button btn_new_account_create;
    Animation ttb , btt;
    ImageView emblem_app;
    TextView intro_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        /**
        //load animasi nya
        ttb = AnimationUtils.loadAnimation(this , R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this , R.anim.btt);

        //daftar id nya
        emblem_app = findViewById(R.id.emblem_app);
        intro_app = findViewById(R.id.intro_app);
         */




        btn_sign_in = findViewById(R.id.btn_sign_in);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignin = new Intent(GetStartedAct.this , SigninAct.class);
                startActivity(gotosignin);
            }
        });

        btn_new_account_create = findViewById(R.id.btn_new_account_create);

        btn_new_account_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone = new Intent(GetStartedAct.this , RegisterOneAct.class );
                startActivity(gotoregisterone);
            }
        });

        /**
        //jalanin animasi
        emblem_app.startAnimation(ttb);
        intro_app.startAnimation(ttb);
        btn_sign_in.startAnimation(btt);
        btn_new_account_create.startAnimation(btt);
         */

    }
}
