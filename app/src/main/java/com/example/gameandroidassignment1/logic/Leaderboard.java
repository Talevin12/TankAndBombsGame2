package com.example.gameandroidassignment1.logic;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<Result> leaderboard = new ArrayList<>();

    public Leaderboard() {  }

    private void sortLeaderboard() {
        leaderboard.sort((result1, result2) -> result2.getScore() - result1.getScore());
    }

    private void saveTop10() {
        while(leaderboard.size() != 10)
            leaderboard.remove(leaderboard.size()-1);
    }

    public void addResult(Result result) {
        leaderboard.add(result);
        sortLeaderboard();
        saveTop10();
    }

    public ArrayList<Result> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<Result> leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "leaderboard=" + leaderboard +
                '}';
    }
}
