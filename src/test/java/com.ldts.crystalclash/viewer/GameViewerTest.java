package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameViewerTest {

    private GUI gui;
    private Board board;
    private GameViewer gameViewer;
    private BoardViewer boardViewer;
    private ScoreViewer scoreViewer;
    private TimerViewer timerViewer;

    @BeforeEach
    public void setup() {

        gui = mock(GUI.class);
        board = mock(Board.class);
        boardViewer = mock(BoardViewer.class);
        scoreViewer = mock(ScoreViewer.class);
        timerViewer = mock(TimerViewer.class);

        gameViewer = new GameViewer(board, boardViewer, scoreViewer, timerViewer);
    }

    @Test
    public void testDrawElementsCallsDrawGameBackground() {
        gameViewer.drawElements(gui);

        verify(gui).drawGameBackground(120, 40);
    }

    @Test
    public void testDrawElementsCallsDrawBoard() {
        gameViewer.drawElements(gui);

        verify(gui).drawBoard(board);
    }

    @Test
    public void testDrawElementsCallsBoardViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(boardViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsScoreViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(scoreViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsTimerViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(timerViewer).drawElements(gui);
    }

    @Test
    public void testDrawCallsClearAndRefresh() throws IOException {
        gameViewer.draw(gui);

        verify(gui).clear();
        verify(gui).refresh();
    }

}
