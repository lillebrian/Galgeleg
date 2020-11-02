package com.example.galgeleg.activities;

import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.galgeleg.BasicGame;
import com.example.galgeleg.GameFactory;
import com.example.galgeleg.R;

public class activity_gamemode extends AppCompatActivity implements View.OnClickListener {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode);

        Button gm_Normal = findViewById(R.id.b_normal);
        gm_Normal.setOnClickListener(this);

        Button gm_Timegated = findViewById(R.id.b_timegated);
        gm_Timegated.setOnClickListener(this);

//        Button gm3 = findViewById(R.id.b_vedikke2);
//        gm3.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        i = new Intent(this, activity_gamescreen.class);
        i.putExtra("gamemode", new GameFactory().chosenGamemode(v));
        startActivity(i);
    }
}