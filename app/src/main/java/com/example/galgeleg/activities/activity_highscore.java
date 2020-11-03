package com.example.galgeleg.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.galgeleg.Highscore;
import com.example.galgeleg.HighscoreAdapter;
import com.example.galgeleg.R;

import java.util.ArrayList;

public class activity_highscore extends AppCompatActivity {
   RecyclerView HighscoreRecyclerView;
   ArrayList<Highscore> hsItems = new ArrayList<>();
   HighscoreAdapter HSAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        HighscoreRecyclerView = findViewById(R.id.hs_recyclerView);

        for (int i = 0; i < 6; i++) {
            hsItems.add(new Highscore("brin".concat(String.valueOf(i)) , i));
        }

        HighscoreRecyclerView.setAdapter(new HighscoreAdapter(hsItems));


    }
}