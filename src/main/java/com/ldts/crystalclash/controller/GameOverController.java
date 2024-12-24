package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.states.ScoresMenuState;
import com.ldts.crystalclash.viewer.GameOverViewer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.List;

public class GameOverController extends Controller<GameOver> {
    ScoresMenu currentScore;

    public GameOverController(GameOver model) {
        super(model);
        try {
            this.currentScore = new ScoresMenu(getModel().getScore());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                if (getModel().isSelectedPlayAgain())
                    game.setState(new GameState(new Board(8, 8, 90, 40, 4, 4)));
                if (getModel().isSelectedScores()) {
                    game.setState(new ScoresMenuState(currentScore));
                }
                break;
            default:
                break;
        }

    }

}
