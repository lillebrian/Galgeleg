package com.example.galgeleg;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class GameTemplate extends Observable implements BasicGame{


    public String bogstav;
    public String guessword;
    public String synligtOrd;
    public int antalForkerte = 0;
    public boolean tabt = false;
    public boolean vundet = false;
    public boolean sidsteBogstavVarKorrekt;
    public long timeLeft;
    public boolean timesUp = false;

    public ArrayList<String> brugteBogstaver = new ArrayList<>();

    public ArrayList<String> muligeOrdFraDr = new ArrayList<>();
//    public Set<String> muligeOrdFraDrSet = new HashSet<>(); // Til preference manager
    public ArrayList<String> muligeOrdValgte = new ArrayList<>();

    public GameTemplate() {
    }

    @Override
    public void readyNextGame() {
        /* RESETTING VARIABLES AND ARRAYLISTS */
        brugteBogstaver.clear();
        antalForkerte = 0;
        vundet = false;
        tabt = false;
        initializeWords();
        if (muligeOrdValgte.isEmpty()) {
            if (muligeOrdFraDr.isEmpty()) {
                throw new IllegalStateException("Listen over mulige ord fra DR er tom!");
            } else {
                throw new IllegalStateException("Listen over spilord er tom! (Check GAMETEMPLATE)");
            }
        }


        /* CHOOSING THE WORD TO BE GUESSED */
        guessword = muligeOrdValgte.get(new Random().nextInt(muligeOrdValgte.size()));
    }

    @Override
    public void startNytSpil() {
        readyNextGame();
        System.out.println("Nyt spil - Det skjulte ord er: " + guessword);
        opdaterSynligtOrd(guessword);
    }


    public void initializeWords() {
        Random random = new Random();
        int upperBound = muligeOrdFraDr.size();
        int pos;
        try {
            for (int i = 0; i < 12; i++) {
                pos = random.nextInt(upperBound);
                if (muligeOrdFraDr.get(pos).length() >= 3)
                    muligeOrdValgte.add(muligeOrdFraDr.get(pos));
            }
            System.out.println(muligeOrdValgte);
        } catch (Exception e) {
            System.out.println("Exception with initializing words.");
        }
    }

    @Override
    public void gætBogstav(String bogstav) {
        if (bogstav.length() != 1) return;
        System.out.println("Der bliver gættet på bogstavet: " + bogstav);
        if (brugteBogstaver.contains(bogstav)) {
            System.out.println("Du har allerede gættet på: " + bogstav);
            return;
        }
        if(bogstav.equals("q")) {
            vundet = true;
        }
        if(tabt || vundet)
            return;

        brugteBogstaver.add(bogstav.toLowerCase());

        if (guessword.contains(bogstav)) {
            sidsteBogstavVarKorrekt = true;
            System.out.println("Bogstavet var korrek: " + bogstav);
        } else {
            System.out.println("Bogstavet var ikke korrekt: " + bogstav);
            sidsteBogstavVarKorrekt = false;
            antalForkerte += 1;
            if (antalForkerte == 6) {
                tabt = true;
            }
        }
        opdaterSynligtOrd(guessword);
    }

    @Override
    public void opdaterSynligtOrd(String guessword) {
        synligtOrd = "";
        vundet = true;
        for (int n = 0; n < guessword.length(); n++) {
            String bogstav = guessword.substring(n, n + 1);
            if (brugteBogstaver.contains(bogstav)) {
                synligtOrd = synligtOrd + bogstav;
            } else {
                synligtOrd = synligtOrd + "*";
                vundet = false;
            }
        }
    }

    @Override
    public void timer(String decider, int seconds) {
        int countdown = seconds*1000;
        if (decider.equals("-")) {
            new CountDownTimer(countdown,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeft = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
                    if (tabt)
                        cancel();

                    setChanged();
                    notifyObservers(timeLeft);
                }
                @Override
                public void onFinish() {
                    tabt = true;
                }
            }.start();
        }
    }








    public String getBogstav() {
        return bogstav;
    }

    public void setBogstav(String bogstav) {
        this.bogstav = bogstav;
    }

    public String getGuessword() {
        return guessword;
    }

    public void setGuessword(String guessword) {
        this.guessword = guessword;
    }

    public String getSynligtOrd() {
        return synligtOrd;
    }

    public void setSynligtOrd(String synligtOrd) {
        this.synligtOrd = synligtOrd;
    }

    public int getAntalForkerte() {
        return antalForkerte;
    }

    public boolean isVundet () {
        return vundet;
    }
    public boolean isTabt () { return tabt; }


    @Override
    public boolean getTimesUp() {
        return timesUp;
    }

    @Override
    public long getTimeLeft() {
        return timeLeft;
    }

    public ArrayList<String> getBrugteBogstaver() {
        return brugteBogstaver;
    }


}
