package com.example.gameandroidassignment1;

import java.util.Random;

public class Bomb {
    private int column;
    private int row;
    private Random rand;
//    private boolean active;

    public Bomb() {
        rand = new Random();
        reset();
//        active = false;
    }

    public void reset() {
        this.column = rand.nextInt(3);
        this.row = -1;
    }

    public void fall() {
        this.row++;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

//    public boolean getActive() { return this.active;}
//
//    public void changeActive() { this.active ^= true;}

}
