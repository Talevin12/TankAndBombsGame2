package com.example.gameandroidassignment1;

import java.util.Random;

public class Bomb {
    private int column;
    private int row;
    private Random rand;

    public Bomb() {
        rand = new Random();
        reset();
    }

    public void reset() {
        this.column = rand.nextInt(3);
        this.row = 0;
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

}
