package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

        import java.util.concurrent.TimeUnit;

class TimerTest {
    private Timer timer;

    @BeforeEach
    void setUp() {
        timer = new Timer();
    }

    @Test
    void testStart() {
        timer.start();
        assertTrue(timer.getElapsedTime() >= 0, "Timer should start with non-negative elapsed time.");
    }

    @Test
    void testGetElapsedTime_AfterStart() throws InterruptedException {
        timer.start();
        TimeUnit.MILLISECONDS.sleep(100);
        long elapsedTime = timer.getElapsedTime();
        assertTrue(elapsedTime >= 100, "Elapsed time should be greater than or equal to 100ms after starting.");
    }

    @Test
    void testGetTimeLeft_AfterStart() throws InterruptedException {
        timer.start();
        TimeUnit.MILLISECONDS.sleep(100);
        long timeLeft = timer.getTimeLeft();
        long elapsedMillis = 100;
        long expectedTimeLeft = (90 * 1000 - elapsedMillis) / 1000;
        assertEquals(expectedTimeLeft, timeLeft, "Time left should reflect elapsed time in seconds.");
    }


    @Test
    void testGetTimeLeft_BeforeStart() {
        long timeLeft = timer.getTimeLeft();
        assertEquals(90, timeLeft, "Time left should be 90 seconds before timer starts.");
    }

    @Test
    void testCancel() {
        timer.start();
        timer.cancel();
        assertEquals(0, timer.getElapsedTime(), "Elapsed time should be reset to 0 after canceling.");
        assertEquals(90, timer.getTimeLeft(), "Time left should be reset to 90 seconds after canceling.");
    }
}
