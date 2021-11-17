package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeAct extends AppCompatActivity {

    LinearLayout btn_ticket_pisa, btn_ticket_torri, btn_ticket_pagoda, btn_ticket_borobudur, btn_ticket_sphinx, btn_ticket_monas;
    CircleView btn_to_profile;
    ImageView photo_home_user , history_pisa , history_monas ,history_torri , history_pagoda, history_borobudur, history_sphinx , selfie_spot;
    TextView user_balance, nama_lengkap, bio;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        //daftarin id nya
        //selfie_spot = findViewById(R.id.selfie_spot);
        history_pisa = findViewById(R.id.history_pisa);
        history_monas = findViewById(R.id.history_monas);
        history_torri = findViewById(R.id.history_torri);
        history_pagoda = findViewById(R.id.history_pagoda);
        history_borobudur = findViewById(R.id.history_borobudur);
        history_sphinx = findViewById(R.id.history_sphinx);

        btn_ticket_pisa = findViewById(R.id.btn_ticket_pisa);
        btn_ticket_torri = findViewById(R.id.btn_ticket_torri);
        btn_ticket_pagoda = findViewById(R.id.btn_ticket_pagoda);
        btn_ticket_borobudur = findViewById(R.id.btn_ticket_borobudur);
        btn_ticket_sphinx = findViewById(R.id.btn_ticket_sphinx);
        btn_ticket_monas = findViewById(R.id.btn_ticket_monas);

        btn_to_profile = findViewById(R.id.btn_to_profile);

        photo_home_user = findViewById(R.id.photo_home_user);
        user_balance = findViewById(R.id.user_balance);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        bio = findViewById(R.id.bio);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nama_lengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                user_balance.setText("US$ "+ dataSnapshot.child("user_balance").getValue().toString());

                Picasso.with(HomeAct.this).load(dataSnapshot.child("url_photo_profile").getValue().toString()).centerCrop().fit().into(photo_home_user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_ticket_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                //meletakan data kepada intent
                gotopisaticket.putExtra("jenis_tiket", "Pisa");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Torri");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Pagoda");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_borobudur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Borobudur");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Sphinx");
                startActivity(gotopisaticket);
            }
        });

        btn_ticket_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotopisaticket = new Intent(HomeAct.this , TicketDetailAct.class);
                gotopisaticket.putExtra("jenis_tiket", "Monas");
                startActivity(gotopisaticket);
            }
        });


        btn_to_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoprofile = new Intent(HomeAct.this , MyProfileAct.class);
                startActivity(gotoprofile);
            }
        });

        history_pisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorypisa = new Intent(HomeAct.this , HistoryPisaAct.class);
                startActivity(gotohistorypisa);
            }
        });

        history_monas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorymonas = new Intent(HomeAct.this , HistoryMonasAct.class);
                startActivity(gotohistorymonas);
            }
        });

        history_torri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorytorri = new Intent(HomeAct.this , HistoryTorriAct.class);
                startActivity(gotohistorytorri);
            }
        });

        history_pagoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistorypagoda = new Intent(HomeAct.this , HistoryPagodaAct.class);
                startActivity(gotohistorypagoda);
            }
        });

        history_borobudur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistoryborobudur = new Intent(HomeAct.this , HistoryBorobudurAct.class);
                startActivity(gotohistoryborobudur);
            }
        });

        history_sphinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistoryborobudur = new Intent(HomeAct.this , HistorySphinxAct.class);
                startActivity(gotohistoryborobudur);
            }
        });

        /**
        selfie_spot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoselfiespot = new Intent(HomeAct.this , SpotSeflieAct.class);
                startActivity(gotoselfiespot);
            }
        });
         */
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
