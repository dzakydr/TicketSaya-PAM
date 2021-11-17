package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessRegisterAct extends AppCompatActivity {

    Button btn_explorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        btn_explorer = findViewById(R.id.btn_explore);

        btn_explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(SuccessRegisterAct.this , HomeAct.class);
                startActivity(gotohome);
            }
        });
    }
}
