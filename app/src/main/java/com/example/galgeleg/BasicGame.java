package com.example.galgeleg;

import com.example.galgeleg.activities.activity_gamescreen;

import java.util.ArrayList;

public interface BasicGame {

    void startNytSpil();

    void gætBogstav(String bogstav);

    void opdaterSynligtOrd(String ord);

    void readyNextGame();

    void timer(String decider, int time);



    /* GETTERS AND SETTERS */
    String getBogstav();
    void setBogstav(String bogstav);
    String getGuessword();
    void setGuessword(String guessword);
    String getSynligtOrd();
    int getAntalForkerte();
    boolean isVundet ();
    boolean isTabt ();
    long getTimeLeft();
    boolean getTimesUp();

    ArrayList<String> getBrugteBogstaver();

}
