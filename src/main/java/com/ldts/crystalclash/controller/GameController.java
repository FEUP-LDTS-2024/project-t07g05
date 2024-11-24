package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.GameViewer;

import java.io.IOException;

public class GameController extends Controller<Board> {
    GameViewer gameViewer;

    public GameController(Board board) {
        super(board);
        this.gameViewer = new GameViewer(board);
    }

    @Override
    public void step(Game game, GUI.ACTION action) throws IOException {
        BoardController boardController = new BoardController(getModel());
        boardController.step(game, action);
    }
}
