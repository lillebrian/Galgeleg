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

//        Button spiligen = findViewById(R.id.won_spiligen);
        Button hovedmenu = findViewById(R.id.won_hovedmenu);

//        spiligen.setOnClickListener(this);
        hovedmenu.setOnClickListener(this);

        TextView ord = findViewById(R.id.won_rigtigtord);
        TextView Desc = findViewById(R.id.won_text);

        Bundle ordFraGame = getIntent().getExtras();
        if (ordFraGame != null) {
            ord.append(ordFraGame.getString("rigtigtord"));
            Desc.setText("Du gættede det rigtige ord på\n" + ordFraGame.getInt("forsøg") + " forsøg");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.won_spiligen:
//                String S = intentBundle.getString("gamemode");
//                i = new Intent(this, activity_gamescreen.class);
//                i.putExtra("gamemode", S);
//                startActivity(i);
//                break;
            case R.id.won_hovedmenu:
                i = new Intent(this, activity_Main.class);
                startActivity(i);
                break;
        }
    }
}
