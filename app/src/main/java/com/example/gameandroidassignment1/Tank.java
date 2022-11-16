package com.example.gameandroidassignment1;

public class Tank {
    private MainActivity.Location location;

    public Tank() {
        this.location = MainActivity.Location.MIDDLE;
    }

    public MainActivity.Location getLocation() {
        return location;
    }

    public void goLeft() {
        if(location == MainActivity.Location.MIDDLE)
            this.location = MainActivity.Location.LEFT;
        else if(location == MainActivity.Location.RIGHT)
            this.location = MainActivity.Location.MIDDLE;
    }

    public void goRight() {
        if(location == MainActivity.Location.MIDDLE)
            this.location = MainActivity.Location.RIGHT;
        else if(location == MainActivity.Location.LEFT)
            this.location = MainActivity.Location.MIDDLE;
    }
}
