package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.states.ScoresMenuState;


import java.io.IOException;

public class MenuController extends Controller<Menu> {

    public MenuController(Menu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().selectPrevious();
                break;
            case DOWN:
                getModel().selectNext();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedPlay())
                    game.setState(new GameState(new Board(8, 8, 90, 40, 4, 4)));
                //completar para o menuscores
                if (getModel().isSelectedScores()) {
                    game.setState(new ScoresMenuState(new ScoresMenu()));
                }
                if (getModel().isSelectedInstructions()) {
                    game.setState();

                }

        }

    }
}