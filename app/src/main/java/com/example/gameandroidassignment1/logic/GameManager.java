package com.example.gameandroidassignment1.logic;

import com.example.gameandroidassignment1.activities.GameActivity;

import java.util.ArrayList;

;

public class GameManager {
    public final int COLUMNS = 5;
    public final int ROWS = 8;
    private int pace;

    private int paceStatus;

    private ArrayList<dropObject> activeDrops;
    private ArrayList<dropObject> inactiveDrops;

    private Tank tank;

    private int score = 0;
    private int wrong = 0;
    private int life;

    private int coins = 0;

    public GameManager(int life, int pace) {
        this.pace = pace;
        this.paceStatus = pace;

        activeDrops = new ArrayList<>();
        inactiveDrops = new ArrayList<>();
        for(int i = 0; i < (ROWS+1)/pace + ((ROWS+1)%pace == 0? 0: 1); i++)
            inactiveDrops.add(new dropObject(i == 2? type.COIN: type.BOMB));

        tank = new Tank();

        this.life = life;
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

    public dropObject getDropLocation(int drop) {
        return activeDrops.get(drop);//new int[]{activeBombs.get(bomb).getRow(), activeBombs.get(bomb).getColumn()};
    }

    public dropObject getFrontDrop() {
        return activeDrops.get(0);
    }

    public void dropsFall() {
        if(paceStatus == pace) {
            activeDrops.add(inactiveDrops.remove(0));
            paceStatus = 1;
        }
        else
            paceStatus++;

        for(dropObject drop : activeDrops)
            drop.fall();
    }

    public int checkDropMeetTankRow() {
        int check = 0;
        if (getFrontDrop().getRow() == ROWS) {
            if(getFrontDrop().getType() == type.BOMB) {
                if (GameActivity.Location.values()[getFrontDrop().getColumn()] == tank.getLocation()) {
                    wrong++;
                    check = 1;
                } else
                    score++;
            }
            else {
                if (GameActivity.Location.values()[getFrontDrop().getColumn()] == tank.getLocation()) {
                    score += 2;
                    check = 2;
                }
            }
            getFrontDrop().reset();
            inactiveDrops.add(getFrontDrop());
            activeDrops.remove(getFrontDrop());
        }

        return check;
    }

    public boolean isGameOver() { return wrong == life; }

    public int getAmountActiveDrops() {
        return this.activeDrops.size();
    }

    public int getScore() {
        return score;
    }

    public int getWrong() {
        return wrong;
    }

    public int getLife() {
        return life;
    }
}