package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneAct extends AppCompatActivity {

    Button btn_continue;
    LinearLayout btn_back;
    EditText username;
    EditText password;
    EditText email_address;
    DatabaseReference reference , reference_username;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        //daftarin id nya
        btn_back = findViewById(R.id.btn_back);
        btn_continue = findViewById(R.id.btn_continue);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email_address = findViewById(R.id.email_address);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ubah state menjadi loading
                btn_continue.setEnabled(false);
                btn_continue.setText("Loading ...");

                //mengambil username pada firebase biar ga double
                reference_username = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());
                reference_username.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //kalo username udah ada
                        if(dataSnapshot.exists()){
                            Toast.makeText(getApplicationContext(),"Masukkan Username Lain , Sayang!", Toast.LENGTH_SHORT).show();

                            // ubah state menjadi active
                            btn_continue.setEnabled(true);
                            btn_continue.setText("LANJUT");
                        } else {
                            //menyimpan data ke local strorage (handphone)
                            SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(username_key, username.getText().toString());
                            editor.apply();

                            //Toast.makeText(getApplicationContext(), "Username " + username.getText().toString(), Toast.LENGTH_SHORT).show();

                            //simpan ke database
                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());

                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                                    dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                                    dataSnapshot.getRef().child("email_address").setValue(email_address.getText().toString());
                                    dataSnapshot.getRef().child("user_balance").setValue(800);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                            //buat pindah activity
                            Intent gotonextregister = new Intent(RegisterOneAct.this, RegisterTwoAct.class);
                            startActivity(gotonextregister);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });






        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtosignin = new Intent(RegisterOneAct.this, SigninAct.class);
                startActivity(backtosignin);
            }
        });

    }
}








