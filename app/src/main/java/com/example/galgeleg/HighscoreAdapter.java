package com.example.galgeleg;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class HighscoreAdapter extends RecyclerView.Adapter<HighscoreAdapter.HighscoreViewHolder> {
    private ArrayList<Highscore> highscores;

    public HighscoreAdapter(String name, int gusses) {
        highscores.add(new Highscore(name,gusses));
    }

    public HighscoreAdapter(ArrayList<Highscore> highscores) {
        this.highscores = highscores;
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
        holder.textGuesses.setText(highscores.get(position).getGuesses());
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
    /* Singleton decleration */
//    private static HighscoreAdapter INSTANCE = null;
//    public HighscoreAdapter() {
//    }
//    public static HighscoreAdapter getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new HighscoreAdapter();
//        }
//        return (INSTANCE);
//    }

//    private Activity activity;
//    private static LayoutInflater inflater = null;
//    private ArrayList<Highscore> hsItems;
//
//
//
//    public HighscoreAdapter (Activity activity, int textViewResourceId, ArrayList<Highscore> highscores) {
//        super(activity, textViewResourceId, highscores);
//        try {
//            this.activity = activity;
//            this.hsItems = highscores;
//
//            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        } catch (Exception e) {
//
//        }
//    }
//
//    public Activity getActivity() {
//        return activity;
//    }
//
//    public static LayoutInflater getInflater() {
//        return inflater;
//    }
//
//    public int getHsItems() {
//        return hsItems.size();
//    }
//
//
//    public static class ViewHolder {
//        public TextView display_name;
//        public TextView display_number;
//    }
//
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View vi = convertView;
//        final ViewHolder holder;
//        try {
//            if (convertView == null) {
//                vi = inflater.inflate(R.layout.activity_highscore, null);
//                holder = new ViewHolder();
//
//                holder.display_name = vi.findViewById(R.id.hs_user);
//                holder.display_number = vi.findViewById(R.id.hs_user);
//
//
//                vi.setTag(holder);
//            } else {
//                holder = (ViewHolder) vi.getTag();
//            }
//
//
//
//            holder.display_name.setText(hsItems.get(position).getName());
//            holder.display_number.setText(hsItems.get(position).getGuesses());
//
//
//        } catch (Exception e) {
//        }
//
//        return vi;
//
//    }



}
