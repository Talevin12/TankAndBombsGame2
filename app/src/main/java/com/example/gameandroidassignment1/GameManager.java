package com.example.gameandroidassignment1;

public class GameManager {
    private Bomb[] bombs;
    private Tank tank;

    private int score = 0;
    private int index = 0;
    private int wrong = 0;
    private int life;

    public GameManager(int life) {
        bombs = new Bomb[1];
        for(int i = 0; i < bombs.length; i++)
            bombs[i] = new Bomb();

        tank = new Tank();

        this.life = life;
    }

    public void tankGoRight() {
        tank.goRight();
    }

    public void tankGoLeft() {
        tank.goLeft();
    }

    public MainActivity.Location getTankLocation() {
        return tank.getLocation();
    }

    public int[] getBombLocation(int bomb) {
        return new int[] {bombs[bomb].getRow(), bombs[bomb].getColumn()};
    }

    public void bombsFall() {
        for(Bomb bomb : bombs)
            bomb.fall();
    }

    public boolean checkBombsExploding() {
        for(Bomb bomb : bombs) {
            if(bomb.getRow() == 5) {
                if(MainActivity.Location.values()[bomb.getColumn()] == tank.getLocation()) {
                    wrong++;

                    if(wrong == life)
                        return true;
                }
                else
                    score++;

                bomb.reset();
                return false;
            }
        }

        return false;
    }

    public int getScore() {
        return score;
    }

    public int getWrong() {
        return wrong;
    }
}
