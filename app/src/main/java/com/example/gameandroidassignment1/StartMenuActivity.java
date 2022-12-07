package com.example.gameandroidassignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class StartMenuActivity  extends AppCompatActivity {

    private MaterialButton startMenu_BTN_easy;
    private MaterialButton startMenu_BTN_hard;
    private MaterialButton startMenu_BTN_sensor;
    private MaterialButton startMenu_BTN_leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);

        String backgroundImg ="https://uhdwallpapers.org/uploads/converted/18/10/17/battlefield-5-poster-with-tanks-wallpaper-1080x1920_86968-mm-90.jpg";
        Glide.with(this)
                .load(backgroundImg)
                .fitCenter()
                .into((ImageView) findViewById(R.id.startMenu_IMG_background));

        findButtons();
        initButtons();
    }

    private void findButtons() {
        startMenu_BTN_easy = findViewById(R.id.startMenu_BTN_easy);
        startMenu_BTN_hard = findViewById(R.id.startMenu_BTN_hard);
        startMenu_BTN_sensor = findViewById(R.id.startMenu_BTN_sensor);
        startMenu_BTN_leaderboard = findViewById(R.id.startMenu_BTN_leaderboard);
    }

    private void initButtons() {
        startMenu_BTN_easy.setOnClickListener(v -> openGamePage(3));

        startMenu_BTN_hard.setOnClickListener(v -> openGamePage(2));

        startMenu_BTN_sensor.setOnClickListener(v -> openGamePage(-1));

        startMenu_BTN_leaderboard.setOnClickListener(view -> openLeaderboardPage());
    }

    private void openGamePage(int difficulty) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(GameActivity.KEY_DIFFICULTY, difficulty);
        startActivity(intent);
    }

    private void openLeaderboardPage() {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        intent.putExtra(LeaderboardActivity.KEY_SCORE, -1);
        startActivity(intent);
    }
}