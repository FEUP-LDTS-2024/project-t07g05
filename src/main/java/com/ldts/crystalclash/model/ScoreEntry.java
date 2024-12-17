package com.ldts.crystalclash.model;

public class ScoreEntry {
    private final String points;
    private final String date;

    public ScoreEntry(String points, String date) {
        this.points = points;
        this.date = date;
    }

    public String getPoints() {
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

