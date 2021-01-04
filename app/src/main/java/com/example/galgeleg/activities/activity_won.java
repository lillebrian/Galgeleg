package com.example.galgeleg.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.galgeleg.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.*;

public class activity_won extends AppCompatActivity implements View.OnClickListener {
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);


//        Konfetti koden er som set på https://github.com/DanielMartinus/Konfetti


//        Button spiligen = findViewById(R.id.won_spiligen);
        Button hovedmenu = findViewById(R.id.won_hovedmenu);

//        spiligen.setOnClickListener(this);
        hovedmenu.setOnClickListener(this);

        TextView ord = findViewById(R.id.won_rigtigtord);
        TextView Desc = findViewById(R.id.won_text);

//        ViewGroup viewGroup = findViewById(android.R.id.content);
//        ConfettiSource confettiSource = new ConfettiSource(viewGroup.getWidth()/2, viewGroup.getHeight()/2);
//        CommonConfetti.rainingConfetti(viewGroup, new int[] { Color.BLUE }).stream(1000);

        Bundle ordFraGame = getIntent().getExtras();
        if (ordFraGame != null) {
            ord.append(ordFraGame.getString("rigtigtord"));
            Desc.setText("Du gættede det rigtige ord på\n" + ordFraGame.getInt("forsøg") + " forsøg");
        }




//        ViewTreeObserver viewTreeObserver = new ViewTreeObserver().addOnDrawListener(R.layout.activity_won);
        // https://github.com/DanielMartinus/Konfetti 
        final KonfettiView konfettiView = findViewById(R.id.viewKonfetti);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f,  50f, -50f, -50f)
                .streamFor(222, 5000L);
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
