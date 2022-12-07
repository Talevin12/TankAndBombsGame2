package com.example.gameandroidassignment1;

import java.util.Random;

public class dropObject {
    private int column;
    private int row;
    private Random rand;
    private type type;


    public dropObject(type type) {
        rand = new Random();
        reset();
        this.type = type;
    }

    public void reset() {
        this.column = rand.nextInt(5);
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

    public type getType() {
        return type;
    }
}
