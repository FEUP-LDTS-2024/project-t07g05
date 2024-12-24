package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;

public class GameViewer extends Viewer<Board> {
    private final BoardViewer boardViewer;
    private final ScoreViewer scoreViewer;
    private final TimerViewer timerViewer;
    private final int width = 120;
    private final int height = 40;

    public GameViewer(Board model, BoardViewer boardViewer, ScoreViewer scoreViewer, TimerViewer timerViewer) {
        super(model);
        this.boardViewer = boardViewer;
        this.scoreViewer = scoreViewer;
        this.timerViewer = timerViewer;
    }

    @Override
    public void drawElements(GUI gui) {
        try {
            gui.drawGameBackground(width, height);
            gui.drawBoard(getModel());
            boardViewer.drawElements(gui);
            scoreViewer.drawElements(gui);
            timerViewer.drawElements(gui);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

