package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.ScoresMenu;

import java.io.IOException;

public class ScoresMenuController extends Controller<ScoresMenu> {
    public ScoresMenuController(ScoresMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {

    }
}
