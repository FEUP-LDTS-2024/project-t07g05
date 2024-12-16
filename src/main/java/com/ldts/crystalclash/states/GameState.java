package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.viewer.Viewer;

public class GameState extends State<Board>{
    public GameState(Board board) {
        super(board);
    }

    @Override
    protected Viewer<Board> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Board> getController() {
        return new GameController(getModel());
    }
}
