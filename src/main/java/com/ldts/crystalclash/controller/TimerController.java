package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.viewer.TimerViewer;

import java.io.IOException;

public class TimerController extends Controller<Timer> {
    private final Game game;
    private Thread updateThread;
    private TimerViewer timerViewer;
    public TimerController(Timer model, Game game) {
        super(model);
        this.game = game;
        this.timerViewer = new TimerViewer(model);
    }

    public void startTimer() {
        getModel().start();

        Thread updateThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); // Update every second
                    step(game, null); // Call step periodically
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.updateThread = updateThread;
        updateThread.start();
    }

    public void stopTimer() {
        if (updateThread != null && updateThread.isAlive()) {
            updateThread.interrupt();
        }
        getModel().cancel();
    }

    @Override
    public void step(Game game, GUI.ACTION action) throws IOException {
        long timeLeft = getModel().getTimeLeft();
        timerViewer.draw(game.gui);
        if (timeLeft <= 0) {
            stopTimer();
        }
    }
}
