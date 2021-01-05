package com.example.galgeleg.activities;
import com.example.galgeleg.GameFactory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.galgeleg.GameTemplate;
import com.example.galgeleg.R;

import java.util.Collections;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

public class activity_gamescreen extends AppCompatActivity implements View.OnClickListener, Observer {
    EditText inBogstav;
    TextView forkerte;
    TextView synligtOrd;
    TextView timer;
    ImageView galgeImage;
    Intent i;
    Bundle recieveI;
    String gamemode;
    GameTemplate logic;
    Long timeLeft;

    /* For preference manager */
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "userName";
    public static final String GUESSES = "forsøg";
    public static final String ORDFRADR = "ordFraDr";
    String userName;
    int forsøg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamescreen);

        /* Views til opdatering ved gæt af bogstaver*/
        forkerte = findViewById(R.id.gamescreen_forkert);
        synligtOrd = findViewById(R.id.gamescreen_ordtilgæt);
        galgeImage = findViewById(R.id.gamescreen_galgeImage);
        timer = findViewById(R.id.gamescreen_timer);
        inBogstav = findViewById(R.id.gamescreen_gæt);
        inBogstav.setOnClickListener(this);


        /* Modtage intent fra forrige skærm omkring hvilken type af gamemode knap der blev trykket på*/
        recieveI = getIntent().getExtras();
        if (recieveI != null) {
            gamemode = recieveI.getString("gamemode");
            userName = recieveI.getString("userName");
        }
        /* Bruger GameFactory til initialisering af nyt object af typen bestemt af forrige tryk */
        logic = new GameFactory().factory(gamemode);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Set<String> muligeOrdFraDrSet = new HashSet<>(sharedPreferences.getStringSet(ORDFRADR, Collections.singleton("null")));
        logic.muligeOrdFraDr.addAll(muligeOrdFraDrSet);

        /* Tilføjer klassen til at blive observet*/
        logic.addObserver(this);

        /* Starter nyt spil*/
        logic.startNytSpil();
        updateTimer();
    }

    @Override
    public void onClick(View v) {
        guessingWord();
        updateGraphics();
    }

    public void guessingWord() {
        String bogstav = inBogstav.getText().toString();
        if (bogstav.length() != 1) {
            forkerte.setText("Indtast kun et bogstav!!!");
        } else {
            forsøg ++;
            logic.gætBogstav(bogstav);
        }
    }

    public void updateGraphics(){
        inBogstav.setText("");
        if(logic.getAntalForkerte() != 0) {
            updateImage(galgeImage);
        }
        synligtOrd.setText(logic.getSynligtOrd());

        forkerte.setText("\n");
        forkerte.append("Du har " + logic.getAntalForkerte() + " forkerte: "  + logic.getBrugteBogstaver());

        gameDecided();
    }

    public void updateTimer() {
        timeLeft = logic.getTimeLeft();
        timer.setText(String.valueOf(timeLeft));
    }

    public void updateImage(ImageView image) {
        if(logic.getAntalForkerte() == 1) {
            image.setImageResource(R.drawable.forkert1);
        } else if(logic.getAntalForkerte() == 2) {
            image.setImageResource(R.drawable.forkert2);
        } else if(logic.getAntalForkerte() == 3) {
            image.setImageResource(R.drawable.forkert3);
        } else if(logic.getAntalForkerte() == 4) {
            image.setImageResource(R.drawable.forkert4);
        } else if(logic.getAntalForkerte() == 5) {
            image.setImageResource(R.drawable.forkert5);
        } else if(logic.getAntalForkerte() == 6) {
            image.setImageResource(R.drawable.forkert6);
        }
    }

    public void gameDecided() {
        recieveI = new Bundle();


        /* LAV TIL OBSERVET OG IKKE TJEK HVER GANG*/
        if (logic.isVundet()) {
            i = new Intent(this, activity_won.class);
            recieveI.putString("gamemode", gamemode);
            recieveI.putString("rigtigtord",logic.getGuessword());
            recieveI.putInt("forsøg",forsøg);
            i.putExtras(recieveI);

            saveData();

            startActivity(i);
        } else if (logic.isTabt()){
            i = new Intent(this, activity_lost.class);
            recieveI.putString("gamemode", gamemode);
            recieveI.putString("rigtigtord",logic.getGuessword());
            i.putExtras(recieveI);
            startActivity(i);
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME, userName);
        editor.putInt(GUESSES, forsøg);
        editor.apply();
    }

    public void loadData() {


    }

    @Override
    public void update(Observable o, Object arg) {
        updateTimer();
    }
}