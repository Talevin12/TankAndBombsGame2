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
        if(location == GameActivity.Location.RIGHT)
            location = GameActivity.Location.RIGHT_MIDDLE;
        else if(location == GameActivity.Location.RIGHT_MIDDLE)
            location = GameActivity.Location.MIDDLE;
        else if(location == GameActivity.Location.MIDDLE)
            location = GameActivity.Location.LEFT_MIDDLE;
        else if(location == GameActivity.Location.LEFT_MIDDLE)
            location = GameActivity.Location.LEFT;
    }

    public void goRight() {
        if(location == GameActivity.Location.LEFT)
            location = GameActivity.Location.LEFT_MIDDLE;
        else if(location == GameActivity.Location.LEFT_MIDDLE)
            location = GameActivity.Location.MIDDLE;
        else if(location == GameActivity.Location.MIDDLE)
            location = GameActivity.Location.RIGHT_MIDDLE;
        else if(location == GameActivity.Location.RIGHT_MIDDLE)
            location = GameActivity.Location.RIGHT;
    }
}