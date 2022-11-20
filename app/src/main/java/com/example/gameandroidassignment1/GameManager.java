package com.example.gameandroidassignment1;

import java.util.ArrayList;

public class GameManager {
    public final int COLUMNS = 3;
    public final int ROWS = 5;
    private int pace;

    private int paceStatus;

    private ArrayList<Bomb> activeBombs;
    private ArrayList<Bomb> inactiveBombs;

    private Tank tank;

    private int score = 0;
    private int wrong = 0;
    private int life;
    private boolean gameOverflag;

    public GameManager(int life, int pace) {
        this.pace = pace;
        this.paceStatus = pace;

        activeBombs = new ArrayList<>();
        inactiveBombs = new ArrayList<>();
        for(int i = 0; i < (ROWS+1)/pace + ((ROWS+1)%pace == 0? 0: 1); i++)
            inactiveBombs.add(new Bomb());

        tank = new Tank();

        this.life = life;
        this.gameOverflag = false;
    }

    public void tankGoRight() {
        tank.goRight();
    }

    public void tankGoLeft() {
        tank.goLeft();
    }

    public GameActivity.Location getTankLocation() {
        return tank.getLocation();
    }

    public Bomb getBombLocation(int bomb) {
        return activeBombs.get(bomb);//new int[]{activeBombs.get(bomb).getRow(), activeBombs.get(bomb).getColumn()};
    }

    public Bomb getFrontBomb() {
        return activeBombs.get(0);
    }

    public void bombsFall() {
        if(paceStatus == pace) {
            activeBombs.add(inactiveBombs.remove(0));
            paceStatus = 1;
        }
        else
            paceStatus++;

        for(Bomb bomb : activeBombs)
            bomb.fall();
    }

    public boolean checkBombsExploding() {
        boolean check = false;
        if (getFrontBomb().getRow() == ROWS) {
            if (GameActivity.Location.values()[getFrontBomb().getColumn()] == tank.getLocation()) {
                wrong++;
                check = true;
            } else if(!gameOverflag)
                score++;

            getFrontBomb().reset();
            inactiveBombs.add(getFrontBomb());
            activeBombs.remove(getFrontBomb());
        }

        return check;
    }

    public boolean isGameOver() {
        if(wrong == life) {
            gameOverflag = true;
            return true;
        }

        return false;
    }

    public int getAmountActiveBombs() {
        return this.activeBombs.size();
    }

    public int getScore() {
        return score;
    }

    public int getWrong() {
        return wrong;
    }

    public void resetGame() {
        this.wrong = 0;
    }

    public int getLife() {
        return life;
    }
}