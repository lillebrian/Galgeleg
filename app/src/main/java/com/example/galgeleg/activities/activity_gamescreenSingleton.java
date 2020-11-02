package com.example.galgeleg.activities;

public class activity_gamescreenSingleton {

    private static activity_gamescreenSingleton INSTANCE = null;

    // other instance variables can be here

    private activity_gamescreenSingleton() {
    }


    public static activity_gamescreenSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new activity_gamescreenSingleton();
        }
        return (INSTANCE);
    }

    // other instance methods can follow
}

