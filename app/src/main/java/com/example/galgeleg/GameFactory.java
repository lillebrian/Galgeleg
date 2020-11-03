package com.example.galgeleg;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.galgeleg.Logic.gm_Normal;
import com.example.galgeleg.Logic.gm_Timegated;


public class GameFactory extends AppCompatActivity {
    public GameFactory() {
    }

    public String chosenGamemode(View v) {
        switch (v.getId()) {
            case R.id.b_normal:
                return "normal";
            case R.id.b_timegated:
                return "timegated";
        }
        return null;
    }

    public GameTemplate factory(String s) {
        switch (s) {
            case "normal":
                return new gm_Normal();
            case "timegated":
                return new gm_Timegated();
        }
        return null;
    }
}