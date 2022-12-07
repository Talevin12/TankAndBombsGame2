package com.example.gameandroidassignment1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Result {
    private int score = 0;
    private String date;

    public Result() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

    public int getScore() {
        return score;
    }

    public Result setScore(int score) {
        this.score = score;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Result setDate(String date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "score=" + score +
                ", date='" + date + '\'' +
                '}';
    }
}
