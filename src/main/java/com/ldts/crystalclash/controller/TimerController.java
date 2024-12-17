package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Timer;

import java.io.IOException;

public class TimerController extends Controller<Timer> {
    public TimerController(Timer model) {
        super(model);
    }

    public void startTimer() {
        getModel().start();
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}
