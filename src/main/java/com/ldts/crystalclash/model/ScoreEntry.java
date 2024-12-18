package com.ldts.crystalclash.model;

public class ScoreEntry {
    private final int points;
    private final String date;

    public ScoreEntry(int points, String date) {
        this.points = points;
        this.date = date;
    }

    public int getPoints() {
        return points;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "ScoreEntry{points='" + points + "', date='" + date + "'}";
    }
}

