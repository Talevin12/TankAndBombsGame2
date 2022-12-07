package com.example.gameandroidassignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    public static final String KEY_DIFFICULTY = "KEY_DIFFICULTY";

    public enum Location {LEFT, LEFT_MIDDLE, MIDDLE, RIGHT_MIDDLE, RIGHT}

    private MaterialTextView game_LBL_score;
    private ShapeableImageView[] hearts;
    private ShapeableImageView[][] bombImgs;
    private ShapeableImageView[][] coinImgs;
    private Map<Location, ShapeableImageView> tanks;
    private MaterialButton game_BTN_left;
    private MaterialButton game_BTN_right;

    private GameManager gameManager;

    int DELAY = 500;

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

        coinImgs = new ShapeableImageView[gameManager.ROWS][gameManager.COLUMNS];
        findCoins();

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
        bombImgs[0][3] = findViewById(R.id.game_IMG_bomb03);
        bombImgs[0][4] = findViewById(R.id.game_IMG_bomb04);

        bombImgs[1][0] = findViewById(R.id.game_IMG_bomb10);
        bombImgs[1][1] = findViewById(R.id.game_IMG_bomb11);
        bombImgs[1][2] = findViewById(R.id.game_IMG_bomb12);
        bombImgs[1][3] = findViewById(R.id.game_IMG_bomb13);
        bombImgs[1][4] = findViewById(R.id.game_IMG_bomb14);

        bombImgs[2][0] = findViewById(R.id.game_IMG_bomb20);
        bombImgs[2][1] = findViewById(R.id.game_IMG_bomb21);
        bombImgs[2][2] = findViewById(R.id.game_IMG_bomb22);
        bombImgs[2][3] = findViewById(R.id.game_IMG_bomb23);
        bombImgs[2][4] = findViewById(R.id.game_IMG_bomb24);

        bombImgs[3][0] = findViewById(R.id.game_IMG_bomb30);
        bombImgs[3][1] = findViewById(R.id.game_IMG_bomb31);
        bombImgs[3][2] = findViewById(R.id.game_IMG_bomb32);
        bombImgs[3][3] = findViewById(R.id.game_IMG_bomb33);
        bombImgs[3][4] = findViewById(R.id.game_IMG_bomb34);

        bombImgs[4][0] = findViewById(R.id.game_IMG_bomb40);
        bombImgs[4][1] = findViewById(R.id.game_IMG_bomb41);
        bombImgs[4][2] = findViewById(R.id.game_IMG_bomb42);
        bombImgs[4][3] = findViewById(R.id.game_IMG_bomb43);
        bombImgs[4][4] = findViewById(R.id.game_IMG_bomb44);

        bombImgs[5][0] = findViewById(R.id.game_IMG_bomb50);
        bombImgs[5][1] = findViewById(R.id.game_IMG_bomb51);
        bombImgs[5][2] = findViewById(R.id.game_IMG_bomb52);
        bombImgs[5][3] = findViewById(R.id.game_IMG_bomb53);
        bombImgs[5][4] = findViewById(R.id.game_IMG_bomb54);

        bombImgs[6][0] = findViewById(R.id.game_IMG_bomb60);
        bombImgs[6][1] = findViewById(R.id.game_IMG_bomb61);
        bombImgs[6][2] = findViewById(R.id.game_IMG_bomb62);
        bombImgs[6][3] = findViewById(R.id.game_IMG_bomb63);
        bombImgs[6][4] = findViewById(R.id.game_IMG_bomb64);

        bombImgs[7][0] = findViewById(R.id.game_IMG_bomb70);
        bombImgs[7][1] = findViewById(R.id.game_IMG_bomb71);
        bombImgs[7][2] = findViewById(R.id.game_IMG_bomb72);
        bombImgs[7][3] = findViewById(R.id.game_IMG_bomb73);
        bombImgs[7][4] = findViewById(R.id.game_IMG_bomb74);
    }

    private void findCoins() {
        coinImgs[0][0] = findViewById(R.id.game_IMG_coin00);
        coinImgs[0][1] = findViewById(R.id.game_IMG_coin01);
        coinImgs[0][2] = findViewById(R.id.game_IMG_coin02);
        coinImgs[0][3] = findViewById(R.id.game_IMG_coin03);
        coinImgs[0][4] = findViewById(R.id.game_IMG_coin04);

        coinImgs[1][0] = findViewById(R.id.game_IMG_coin10);
        coinImgs[1][1] = findViewById(R.id.game_IMG_coin11);
        coinImgs[1][2] = findViewById(R.id.game_IMG_coin12);
        coinImgs[1][3] = findViewById(R.id.game_IMG_coin13);
        coinImgs[1][4] = findViewById(R.id.game_IMG_coin14);

        coinImgs[2][0] = findViewById(R.id.game_IMG_coin20);
        coinImgs[2][1] = findViewById(R.id.game_IMG_coin21);
        coinImgs[2][2] = findViewById(R.id.game_IMG_coin22);
        coinImgs[2][3] = findViewById(R.id.game_IMG_coin23);
        coinImgs[2][4] = findViewById(R.id.game_IMG_coin24);

        coinImgs[3][0] = findViewById(R.id.game_IMG_coin30);
        coinImgs[3][1] = findViewById(R.id.game_IMG_coin31);
        coinImgs[3][2] = findViewById(R.id.game_IMG_coin32);
        coinImgs[3][3] = findViewById(R.id.game_IMG_coin33);
        coinImgs[3][4] = findViewById(R.id.game_IMG_coin34);

        coinImgs[4][0] = findViewById(R.id.game_IMG_coin40);
        coinImgs[4][1] = findViewById(R.id.game_IMG_coin41);
        coinImgs[4][2] = findViewById(R.id.game_IMG_coin42);
        coinImgs[4][3] = findViewById(R.id.game_IMG_coin43);
        coinImgs[4][4] = findViewById(R.id.game_IMG_coin44);

        coinImgs[5][0] = findViewById(R.id.game_IMG_coin50);
        coinImgs[5][1] = findViewById(R.id.game_IMG_coin51);
        coinImgs[5][2] = findViewById(R.id.game_IMG_coin52);
        coinImgs[5][3] = findViewById(R.id.game_IMG_coin53);
        coinImgs[5][4] = findViewById(R.id.game_IMG_coin54);

        coinImgs[6][0] = findViewById(R.id.game_IMG_coin60);
        coinImgs[6][1] = findViewById(R.id.game_IMG_coin61);
        coinImgs[6][2] = findViewById(R.id.game_IMG_coin62);
        coinImgs[6][3] = findViewById(R.id.game_IMG_coin63);
        coinImgs[6][4] = findViewById(R.id.game_IMG_coin64);

        coinImgs[7][0] = findViewById(R.id.game_IMG_coin70);
        coinImgs[7][1] = findViewById(R.id.game_IMG_coin71);
        coinImgs[7][2] = findViewById(R.id.game_IMG_coin72);
        coinImgs[7][3] = findViewById(R.id.game_IMG_coin73);
        coinImgs[7][4] = findViewById(R.id.game_IMG_coin74);
    }

    private void findTanks() {
        tanks.put(Location.LEFT, findViewById(R.id.game_IMG_tank0));
        tanks.put(Location.LEFT_MIDDLE, findViewById(R.id.game_IMG_tank1));
        tanks.put(Location.MIDDLE, findViewById(R.id.game_IMG_tank2));
        tanks.put(Location.RIGHT_MIDDLE, findViewById(R.id.game_IMG_tank3));
        tanks.put(Location.RIGHT, findViewById(R.id.game_IMG_tank4));
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
        dropObject drop;
        for(int i = 0; i < gameManager.getAmountActiveDrops(); i++) {
            drop = gameManager.getDropLocation(i);
            if(drop.getType() == type.BOMB)
                bombImgs[drop.getRow()][drop.getColumn()].setVisibility(View.INVISIBLE);
            else
                coinImgs[drop.getRow()][drop.getColumn()].setVisibility(View.INVISIBLE);
        }

        gameManager.dropsFall();
        if(gameManager.checkDropMeetTankRow()) {
            vibrate();
            Toast.makeText(getApplicationContext(),"OUCH! ",Toast.LENGTH_SHORT).show();
        }

        if(gameManager.isGameOver()) {
            Intent intent = new Intent(this, LeaderboardActivity.class);
            intent.putExtra(LeaderboardActivity.KEY_SCORE, gameManager.getScore());
            startActivity(intent);
            stopTimer();
            finish();
        }
        else {
            for (int i = 0; i < gameManager.getAmountActiveDrops(); i++) {
                drop = gameManager.getDropLocation(i);
                if(drop.getType() == type.BOMB)
                    bombImgs[drop.getRow()][drop.getColumn()].setVisibility(View.VISIBLE);
                else
                    coinImgs[drop.getRow()][drop.getColumn()].setVisibility(View.VISIBLE);
            }
        }

        game_LBL_score.setText("" + gameManager.getScore());

        for (int i = 0; i < gameManager.getLife(); i++) {
            if(i < gameManager.getWrong())
                hearts[i].setVisibility(View.INVISIBLE);
            else
                hearts[i].setVisibility(View.VISIBLE);
        }

        if(gameManager.getScore()%5 == 0 && gameManager.getScore() != 0 && DELAY >= 100)
            DELAY -= 50;
    }

    private Timer timer = new Timer();

    private void startTimer() {
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> updateUI());
            }
        }, DELAY, DELAY);
    }

    private void stopTimer(){
        timer.cancel();
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