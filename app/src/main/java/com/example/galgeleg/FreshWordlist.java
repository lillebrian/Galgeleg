package com.example.galgeleg;

public class FreshWordlist extends GameTemplate {
    public FreshWordlist() {
        try {
            hentOrdFraDr();
            initializeWords();
        } catch (Exception e) {
            System.out.println("Kan ikke fange ord fra dr!");
        }
    }
}

