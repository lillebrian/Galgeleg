package com.example.galgeleg.Logic;
import com.example.galgeleg.GameTemplate;

public class gm_Timegated extends GameTemplate {
    public gm_Timegated() {}

    @Override
    public void startNytSpil() {
        readyNextGame();
        timer("-",90);
        System.out.println("Nyt spil - Det skjulte ord er: " + guessword);
        opdaterSynligtOrd(guessword);
    }
}
