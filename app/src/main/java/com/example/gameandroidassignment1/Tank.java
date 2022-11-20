package com.example.gameandroidassignment1;

public class Tank {
    private GameActivity.Location location;

    public Tank() {
        this.location = GameActivity.Location.MIDDLE;
    }

    public GameActivity.Location getLocation() {
        return location;
    }

    public void goLeft() {
        if(location == GameActivity.Location.MIDDLE)
            this.location = GameActivity.Location.LEFT;
        else if(location == GameActivity.Location.RIGHT)
            this.location = GameActivity.Location.MIDDLE;
    }

    public void goRight() {
        if(location == GameActivity.Location.MIDDLE)
            this.location = GameActivity.Location.RIGHT;
        else if(location == GameActivity.Location.LEFT)
            this.location = GameActivity.Location.MIDDLE;
    }
}
