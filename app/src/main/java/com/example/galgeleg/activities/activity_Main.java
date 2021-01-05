package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.galgeleg.FreshWordlist;
import com.example.galgeleg.GameFactory;
import com.example.galgeleg.GameTemplate;
import com.example.galgeleg.HighscoreAdapter;
import com.example.galgeleg.R;

public class  activity_Main extends AppCompatActivity implements View.OnClickListener {
    private static boolean FIRST_RUN = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FIRST_RUN) {
            FIRST_RUN = false;
            SharedPreferences sharedPreferences = getSharedPreferences(activity_gamescreen.SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            // Hent ord fra dr fra start
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                        FreshWordlist fwl = new FreshWordlist();
                }
            });
            t1.start();
        }

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