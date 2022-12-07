package com.example.gameandroidassignment1;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

public class LeaderboardActivity extends AppCompatActivity {
    private static final String SP_KEY_LEADERBOARD = "SP_KEY_LEADERBOARD";
    public static final String KEY_SCORE = "KEY_SCORE";
//    public static final String KEY_DATE = "KEY_DATE";

    private Leaderboard leaderboard = new Leaderboard();

    private MaterialTextView leaderboard_LBL_leaders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        MySPV3.init(this);

        updateLeaderboard();
    }

    private void updateLeaderboard(){
        Intent previousIntent = getIntent();
        int last_score = previousIntent.getExtras().getInt(KEY_SCORE);

        read();

        if(last_score != -1) {
            leaderboard.addResult(new Result()
                    .setScore(last_score));

            write();
        }

        findViews();

        leaderboard_LBL_leaders.setText(leaderboard.getLeaderboard().toString());
    }

    private void read() {
        String s = MySPV3.getInstance().getString(SP_KEY_LEADERBOARD, null);
        leaderboard = new Gson().fromJson(s, Leaderboard.class);
        if(leaderboard == null)
            leaderboard = new Leaderboard();
    }

    private void write() {
        MySPV3.getInstance().putString(SP_KEY_LEADERBOARD, new Gson().toJson(leaderboard));
    }

    private void findViews() {
        leaderboard_LBL_leaders = findViewById(R.id.leaderboard_LBL_leaders);
    }
}
