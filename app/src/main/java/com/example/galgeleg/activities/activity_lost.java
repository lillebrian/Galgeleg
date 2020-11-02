package com.example.galgeleg.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.galgeleg.R;

public class activity_lost extends AppCompatActivity implements View.OnClickListener {
    Intent i;
    Bundle intentBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        Button spiligen = findViewById(R.id.lost_spiligen);
        Button hovedmenu = findViewById(R.id.lost_hovedmenu);

        spiligen.setOnClickListener(this);
        hovedmenu.setOnClickListener(this);

        TextView ord = findViewById(R.id.lost_rigtigtord);
        intentBundle = getIntent().getExtras();
        if (intentBundle != null)
            ord.append(intentBundle.getString("rigtigtord"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lost_spiligen:
                i = new Intent(this, activity_gamescreen.class);
                i.putExtra("gamemode", intentBundle.getString("gamemode"));
                startActivity(i);
                break;
            case R.id.lost_hovedmenu:
                i = new Intent(this, activity_Main.class);
                i.putExtra("gamemode", intentBundle.getString("gamemode"));
                startActivity(i);
                break;
        }
    }
}