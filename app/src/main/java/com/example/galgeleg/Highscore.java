package com.example.galgeleg;

import androidx.recyclerview.widget.RecyclerView;

public class Highscore {

    private String name;
    private int guesses;

    public Highscore (String name, int guesses) {
        this.name = name;
        this.guesses = guesses;
    }

    public String getName() {
        return name;
    }

    public int getGuesses() {
        return guesses;
    }
}
