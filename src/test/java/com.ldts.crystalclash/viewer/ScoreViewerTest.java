package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ScoreViewerTest {
    private Score score;
    private ScoreViewer scoreViewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {

        score = mock(Score.class);
        gui = mock(GUI.class);
        scoreViewer = new ScoreViewer(score);
    }

    @Test
    void drawElementsDisplaysScoreCorrectly() {
        when(score.getScore()).thenReturn(150);

        scoreViewer.drawElements(gui);

        verify(gui).drawTextInGame(new Position(100, 5), "SCORE:", "#FFFFFF");
        verify(gui).drawTextInGame(new Position(100, 7), "150", "#FFFFFF");
        verifyNoMoreInteractions(gui);
    }
    @Test
    void drawElementsHandlesException() {
        when(score.getScore()).thenThrow(new RuntimeException("Score retrieval error"));

        assertThrows(RuntimeException.class, () -> scoreViewer.drawElements(gui));
    }
}