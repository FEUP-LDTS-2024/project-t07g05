package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.states.MenuState;

import java.io.IOException;

public class GameController extends Controller<Board> {
    public GameController(Board board) {
        super(board);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT) {
            game.setState(new MenuState(new Menu()));
        }

        if (getModel().getTimer().getTimeLeft() == 0) {
            game.setState(new GameOverState(new GameOver(getModel().getScore().getScore())));
        }

        if (action != null) {
            BoardController boardController = new BoardController(getModel());
            boardController.step(game, action, time);
        }
    }


}
