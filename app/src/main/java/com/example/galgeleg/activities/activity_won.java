package com.example.galgeleg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.galgeleg.R;

public class activity_won extends AppCompatActivity implements View.OnClickListener {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        Button spiligen = findViewById(R.id.won_spiligen);
        Button hovedmenu = findViewById(R.id.won_hovedmenu);

        spiligen.setOnClickListener(this);
        hovedmenu.setOnClickListener(this);

        TextView ord = findViewById(R.id.won_rigtigtord);
        Bundle ordFraGame = getIntent().getExtras();
        if (ordFraGame != null)
            ord.append(ordFraGame.getString("rigtigtord"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.won_spiligen:
                i = new Intent(this, activity_gamescreen.class);
                startActivity(i);
                break;
            case R.id.won_hovedmenu:
                i = new Intent(this, activity_Main.class);
                startActivity(i);
                break;
        }
    }
}
