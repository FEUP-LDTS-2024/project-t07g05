package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;

public class GameViewer extends Viewer<Board> {
    private final int width = 120;
    private final int height = 40;

    public GameViewer(Board model) {
        super(model);
    }

    @Override
    public void drawElements(GUI gui) {
        try {
            gui.drawGameBackground(width, height);

            gui.drawBoard(getModel()); // draws board background
            BoardViewer boardViewer = new BoardViewer(getModel());
            boardViewer.drawElements(gui);

            ScoreViewer scoreViewer = new ScoreViewer(getModel().getScore());
            scoreViewer.drawElements(gui);

            TimerViewer timerViewer = new TimerViewer(getModel().getTimer());
            timerViewer.drawElements(gui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
