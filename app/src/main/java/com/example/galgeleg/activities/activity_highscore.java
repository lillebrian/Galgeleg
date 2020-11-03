package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.galgeleg.Highscore;
import com.example.galgeleg.HighscoreAdapter;
import com.example.galgeleg.R;
import java.util.ArrayList;

public class activity_highscore extends AppCompatActivity {
    RecyclerView HighscoreRecyclerView;
    SharedPreferences sharedPreferences;

    HighscoreAdapter HA = HighscoreAdapter.getInstance();
    ArrayList<Highscore> hsItems = HA.highscores;
    String userName;
    int userGuesses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        loadData();
        HighscoreRecyclerView = findViewById(R.id.hs_recyclerView);
            HighscoreRecyclerView.setAdapter(HA);
    }


    public void loadData() {
            sharedPreferences = getSharedPreferences(activity_gamescreen.SHARED_PREFS, MODE_PRIVATE);
            userName = sharedPreferences.getString(activity_gamescreen.USERNAME,"");
            userGuesses = sharedPreferences.getInt(activity_gamescreen.GUESSES, 0);
            if(userGuesses != 0)
                hsItems.add(new Highscore(userName,userGuesses));
    }
}