package com.example.galgeleg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
    public ArrayList<Highscore> highscores = new ArrayList<>();

    /* Singleton decleration */
    private static HighscoreAdapter INSTANCE = null;
    private HighscoreAdapter () {
    }
    public static HighscoreAdapter getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HighscoreAdapter();
        }
        return INSTANCE;
    }

    public void setHighscore(String s, int i) {
        highscores.add(new Highscore(s,i));
        /* Sorting descending order */
        Collections.sort(highscores);
    }


    @NonNull
    @Override
    public HighscoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HighscoreViewHolder (
            LayoutInflater.from(parent.getContext()).inflate(R.layout.highscore_container, parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HighscoreViewHolder holder, int position) {
        holder.textUser.setText(highscores.get(position).getName());
        holder.textGuesses.setText(String.valueOf(highscores.get(position).getGuesses()));
    }

    @Override
    public int getItemCount() {
        return highscores.size();
    }

    class HighscoreViewHolder extends RecyclerView.ViewHolder {
        private TextView textUser, textGuesses;

       HighscoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textUser = itemView.findViewById(R.id.hs_user);
            textGuesses = itemView.findViewById(R.id.hs_number);
        }
    }


}
