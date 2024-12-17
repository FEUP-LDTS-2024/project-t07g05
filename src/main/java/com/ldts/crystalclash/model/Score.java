package com.ldts.crystalclash.model;

public class Score {
    int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int points) {
        this.score += points;
    }
}
