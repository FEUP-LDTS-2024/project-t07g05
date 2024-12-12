package com.ldts.crystalclash.controller;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.states.GameState;

import com.ldts.crystalclash.states.ScoresState;


import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action) throws IOException {
        switch (action) {
            case UP:
                getModel().selectPrevious();
                break;
            case DOWN:
                getModel().selectNext();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);

                else if (getModel().isSelectedStart())
                    game.setState(new GameState(new Board(8, 8, 120, 40, 4, 4)));
                    //completar
                else if (getModel().isSelectedScores()) {

                }

        }

    }
}