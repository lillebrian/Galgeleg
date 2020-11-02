package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.galgeleg.R;

public class  activity_Main extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startSpil = findViewById(R.id.b_StartSpil);
        startSpil.setOnClickListener(this);

        Button highscore = findViewById(R.id.b_highscore);
        highscore.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.b_StartSpil:
                i = new Intent(this, activity_gamemode.class);
                startActivity(i);
                break;

            case R.id.b_highscore:
                i = new Intent(this, activity_highscore.class);
                startActivity(i);
                break;

        }
    }
}