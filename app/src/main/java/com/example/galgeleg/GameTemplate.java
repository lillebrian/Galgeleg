package com.example.galgeleg;

import android.os.CountDownTimer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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
    public ArrayList<String> muligeOrd = new ArrayList<>();


    public GameTemplate() {

    }



    @Override
    public void readyNextGame() {
        /* RESETTING VARIABLES AND ARRAYLISTS */
        brugteBogstaver.clear();
        antalForkerte = 0;
        vundet = false;
        tabt = false;

        initializeWords(muligeOrd);
        if (muligeOrd.isEmpty()) throw new IllegalStateException("Listen over mulige ord er tom!");
        /* CHOOSING THE WORD TO BE GUESSED */
        guessword = muligeOrd.get(new Random().nextInt(muligeOrd.size()));
    }

    @Override
    public void startNytSpil() {
        readyNextGame();
        System.out.println("Nyt spil - Det skjulte ord er: " + guessword);
        opdaterSynligtOrd(guessword);
    }

    public void initializeWords(ArrayList<String> muligeOrd) {
        try {
//            hentOrdFraDr();
            muligeOrd.add("hej");
        } catch (Exception e) {
            System.out.println("Exception");
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


    public static String hentUrl(String url) throws IOException {
        System.out.println("Henter data fra " + url);
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje).append("\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    public void hentOrdFraDr() throws Exception {
        String data = hentUrl("https://dr.dk");
        //System.out.println("data = " + data);

        data = data.substring(data.indexOf("<body")). // fjern headere
                replaceAll("<.+?>", " ").toLowerCase(). // fjern tags
                replaceAll("&#198;", "æ"). // erstat HTML-tegn
                replaceAll("&#230;", "æ"). // erstat HTML-tegn
                replaceAll("&#216;", "ø"). // erstat HTML-tegn
                replaceAll("&#248;", "ø"). // erstat HTML-tegn
                replaceAll("&oslash;", "ø"). // erstat HTML-tegn
                replaceAll("&#229;", "å"). // erstat HTML-tegn
                replaceAll("[^a-zæøå]", " "). // fjern tegn der ikke er bogstaver
                replaceAll(" [a-zæøå] ", " "). // fjern 1-bogstavsord
                replaceAll(" [a-zæøå][a-zæøå] ", " "); // fjern 2-bogstavsord

        System.out.println("data = " + data);
        System.out.println("data = " + Arrays.asList(data.split("\\s+")));
        muligeOrd.clear();
        muligeOrd.addAll(new HashSet<String>(Arrays.asList(data.split(" "))));

        System.out.println("muligeOrd = " + muligeOrd);
//        startNytSpil();
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
