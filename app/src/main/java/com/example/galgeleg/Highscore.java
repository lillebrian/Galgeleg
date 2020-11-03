package com.example.galgeleg;

import androidx.recyclerview.widget.RecyclerView;

public class Highscore {

    private final String name;
    private final int guesses;

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
