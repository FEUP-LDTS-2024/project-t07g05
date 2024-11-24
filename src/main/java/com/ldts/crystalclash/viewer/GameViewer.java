package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;

public class GameViewer extends Viewer<Board> {
    private final int width = 120;
    private final int height = 40;
    BoardViewer boardViewer;

    public GameViewer(Board model) {
        super(model);
        this.boardViewer = new BoardViewer(model);
    }

    @Override
    public void draw(LanternaGUI gui) {
        try {
            gui.clear();
            gui.drawGameBackground(width, height);
            boardViewer.draw(gui);
            gui.refresh();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
