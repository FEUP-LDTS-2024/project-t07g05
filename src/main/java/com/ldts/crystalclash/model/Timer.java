package com.ldts.crystalclash.model;

import java.util.TimerTask;

public class Timer {
    private static final long TOTAL_TIME = 5 * 1000; // in milliseconds
    private long startTime;


    public Timer() {
        this.startTime = 0;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        // Returns the elapsed time in milliseconds
        if (startTime == 0) {
            return 0;
        }
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime);
    }

    public long getTimeLeft() {
        // Returns the time left in the timer in seconds
        if (startTime == 0) {
            return TOTAL_TIME / 1000;
        }
        long elapsedTime = getElapsedTime();
        long timeLeft = TOTAL_TIME - elapsedTime;

        return timeLeft > 0 ? timeLeft / 1000 : 0;
    }

    public void cancel() {
        startTime = 0;
    }
}
