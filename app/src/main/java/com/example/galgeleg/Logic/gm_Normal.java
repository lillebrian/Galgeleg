package com.example.galgeleg.Logic;
import com.example.galgeleg.*;

public class gm_Normal extends GameTemplate {
    public gm_Normal() {}

    @Override
    public void startNytSpil() {
        readyNextGame();
        System.out.println("Nyt spil - Det skjulte ord er: " + guessword);
        opdaterSynligtOrd(guessword);
    }
}
