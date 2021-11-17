package com.dzakyapps.tiketsaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryMonasAct extends AppCompatActivity {

    Button btn_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_monas);

        btn_dashboard = findViewById(R.id.btn_dashboard);

        btn_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtodashboard = new Intent(HistoryMonasAct.this , HomeAct.class);
                startActivity(backtodashboard);
            }
        });
    }
}
