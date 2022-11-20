package com.example.gameandroidassignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    public static final String KEY_DIFFICULTY = "KEY_DIFFICULTY";

    public enum Location {LEFT, MIDDLE, RIGHT}

    private TextView game_LBL_score;
    private ShapeableImageView[] hearts;
    private ShapeableImageView[][] bombImgs;
    private Map<Location, ShapeableImageView> tanks;
    private MaterialButton game_BTN_left;
    private MaterialButton game_BTN_right;

    private GameManager gameManager;

    final int DELAY = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        String backgroundImg ="https://www.fonewalls.com/wp-content/uploads/Sand-Surface-Desert-Wallpaper-1080x1920.jpg";
        Glide.with(this)
                .load(backgroundImg)
                .fitCenter()
                .into((ImageView) findViewById(R.id.game_IMG_background));

        game_LBL_score = findViewById(R.id.game_LBL_score);

        hearts = new ShapeableImageView[3];
        findHearts();

        Intent previousIntent = getIntent();
        int pace = previousIntent.getExtras().getInt(KEY_DIFFICULTY);
        gameManager = new GameManager(hearts.length, pace);

        bombImgs = new ShapeableImageView[gameManager.ROWS][gameManager.COLUMNS];
        findBombs();

        tanks = new HashMap<>();
        findTanks();

        findButtons();

        initViews();

        startTimer();
    }

    private void findHearts() {
        hearts[0] = findViewById(R.id.game_IMG_heart1);
        hearts[1] = findViewById(R.id.game_IMG_heart2);
        hearts[2] = findViewById(R.id.game_IMG_heart3);
    }

    private void findBombs() {
        bombImgs[0][0] = findViewById(R.id.game_IMG_bomb00);
        bombImgs[0][1] = findViewById(R.id.game_IMG_bomb01);
        bombImgs[0][2] = findViewById(R.id.game_IMG_bomb02);

        bombImgs[1][0] = findViewById(R.id.game_IMG_bomb10);
        bombImgs[1][1] = findViewById(R.id.game_IMG_bomb11);
        bombImgs[1][2] = findViewById(R.id.game_IMG_bomb12);

        bombImgs[2][0] = findViewById(R.id.game_IMG_bomb20);
        bombImgs[2][1] = findViewById(R.id.game_IMG_bomb21);
        bombImgs[2][2] = findViewById(R.id.game_IMG_bomb22);

        bombImgs[3][0] = findViewById(R.id.game_IMG_bomb30);
        bombImgs[3][1] = findViewById(R.id.game_IMG_bomb31);
        bombImgs[3][2] = findViewById(R.id.game_IMG_bomb32);

        bombImgs[4][0] = findViewById(R.id.game_IMG_bomb40);
        bombImgs[4][1] = findViewById(R.id.game_IMG_bomb41);
        bombImgs[4][2] = findViewById(R.id.game_IMG_bomb42);
    }

    private void findTanks() {
        tanks.put(Location.LEFT, findViewById(R.id.game_IMG_tank0));
        tanks.put(Location.MIDDLE, findViewById(R.id.game_IMG_tank1));
        tanks.put(Location.RIGHT, findViewById(R.id.game_IMG_tank2));
    }

    private void findButtons() {
        this.game_BTN_left = findViewById(R.id.game_BTN_left);
        this.game_BTN_right = findViewById(R.id.game_BTN_right);
    }

    private void initViews() {
        game_BTN_left.setOnClickListener(v -> {
            tanks.get(gameManager.getTankLocation()).setVisibility(View.INVISIBLE);
            gameManager.tankGoLeft();
            tanks.get(gameManager.getTankLocation()).setVisibility(View.VISIBLE);
        });

        game_BTN_right.setOnClickListener(v -> {
            tanks.get(gameManager.getTankLocation()).setVisibility(View.INVISIBLE);
            gameManager.tankGoRight();
            tanks.get(gameManager.getTankLocation()).setVisibility(View.VISIBLE);
        });
    }

    private void updateUI() {
        Bomb bomb;
        for(int i = 0; i < gameManager.getAmountActiveBombs(); i++) {
            bomb = gameManager.getBombLocation(i);
            bombImgs[bomb.getRow()][bomb.getColumn()].setVisibility(View.INVISIBLE);
        }

        gameManager.bombsFall();
        if(gameManager.checkBombsExploding()) {
            vibrate();
            Toast.makeText(getApplicationContext(),"OUCH! ",Toast.LENGTH_SHORT).show();
        }

        if(gameManager.isGameOver()) {
            gameManager.resetGame();
        }
        else {
            for (int i = 0; i < gameManager.getAmountActiveBombs(); i++) {
                bomb = gameManager.getBombLocation(i);
                bombImgs[bomb.getRow()][bomb.getColumn()].setVisibility(View.VISIBLE);
            }
        }

        game_LBL_score.setText("" + gameManager.getScore());

        for (int i = 0; i < gameManager.getLife(); i++) {
            if(i < gameManager.getWrong())
                hearts[i].setVisibility(View.INVISIBLE);
            else
                hearts[i].setVisibility(View.VISIBLE);
        }
    }

    private Timer timer = new Timer();

    private void startTimer() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        updateUI();
                    }
                });
            }
        }, DELAY, DELAY);
    }

    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }
}