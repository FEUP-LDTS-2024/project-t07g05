package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.*;

public class GameState extends State<Board>{
    public GameState(Board board) {
        super(board);
    }

    @Override
    protected Viewer<Board> getViewer() {
        BoardViewer boardViewer = new BoardViewer(getModel());
        ScoreViewer scoreViewer = new ScoreViewer(getModel().getScore());
        TimerViewer timerViewer = new TimerViewer(getModel().getTimer());

        return new GameViewer(getModel(), boardViewer, scoreViewer, timerViewer);
    }

    @Override
    protected Controller<Board> getController() {
        return new GameController(getModel());
    }
}