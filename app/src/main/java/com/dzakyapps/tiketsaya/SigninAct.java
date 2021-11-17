package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class SigninAct extends AppCompatActivity {

    TextView btn_new_account;
    Button btn_sign_in;
    EditText xusername , xpassword;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //inisialisasi id
        btn_new_account = findViewById(R.id.btn_new_account);
        btn_sign_in = findViewById(R.id.btn_sign_in);
        xusername = findViewById(R.id.xusername);
        xpassword = findViewById(R.id.xpassword);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoregisterone  = new Intent(SigninAct.this , RegisterOneAct.class);
                startActivity(gotoregisterone);
            }
        });



        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ubah state menjadi loading
                btn_sign_in.setEnabled(false);
                btn_sign_in.setText("Loading ...");

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Masukin Username Dahulu , Sayang!" , Toast.LENGTH_SHORT).show();
                    // mengembalikan state loadin menjadi masuk atau sign in
                    btn_sign_in.setEnabled(true);
                    btn_sign_in.setText("MASUK");
                }
                else {
                    if(password.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Masukin Password Juga , Sayang!" , Toast.LENGTH_SHORT).show();
                        // mengembalikan state loadin menjadi masuk atau sign in
                        btn_sign_in.setEnabled(true);
                        btn_sign_in.setText("MASUK");
                    }
                    else {
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    //Toast.makeText(getApplicationContext(),"Username Ada , Sayang!" , Toast.LENGTH_SHORT).show();

                                    //ambil data password dari firebase
                                    String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                                    //validasi
                                    if (password.equals(passwordFromFirebase)) {
                                        //simpan username key ke local storage
                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        //pindah activity
                                        Intent gotohome = new Intent(SigninAct.this , HomeAct.class);
                                        startActivity(gotohome);
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Masukin Password Yang Benar , Sayang!" , Toast.LENGTH_SHORT).show();
                                        // mengembalikan state loadin menjadi masuk atau sign in
                                        btn_sign_in.setEnabled(true);
                                        btn_sign_in.setText("MASUK");
                                    }


                                } else {
                                    Toast.makeText(getApplicationContext(),"Username Tidak Ada , Sayang!" , Toast.LENGTH_SHORT).show();
                                    // mengembalikan state loadin menjadi masuk atau sign in
                                    btn_sign_in.setEnabled(true);
                                    btn_sign_in.setText("MASUK");

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Database Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}
