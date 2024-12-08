package com.ldts.crystalclash.model;

import java.util.TimerTask;

public class Timer {
    private static final long TOTAL_TIME = 90 * 1000; // in milliseconds
    private long startTime;
    private java.util.Timer timer;
    private Runnable callback;


    public Timer(Runnable callback) {
        this.startTime = 0;
        this.timer = new java.util.Timer();
        this.callback = callback;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.run();
                    System.out.println("Time has finished!");
                }
            }
        };
        timer.schedule(task, TOTAL_TIME);
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
        if (timer != null) {
            timer.cancel();
        }
        startTime = 0;
    }
}
