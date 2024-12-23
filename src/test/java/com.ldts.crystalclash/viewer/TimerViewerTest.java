package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TimerViewerTest {
    private TimerViewer timerViewer;
    private Timer timer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        timer = mock(Timer.class);
        timerViewer = new TimerViewer(timer);
    }

    @Test
    void drawElementsDisplaysTimeLeftCorrectly() {
        when(timer.getTimeLeft()).thenReturn(30L);

        timerViewer.drawElements(gui);

        verify(gui).drawTextInGame(new Position(100, 12), "TIME LEFT:", "#FFFFFF");
        verify(gui).drawTextInGame(new Position(100, 14), "30", "#FFFFFF");
        verifyNoMoreInteractions(gui);
    }
    @Test
    void drawElementsDisplaysZeroTimeLeft() {
        when(timer.getTimeLeft()).thenReturn(0L);

        timerViewer.drawElements(gui);

        verify(gui).drawTextInGame(new Position(100, 12), "TIME LEFT:", "#FFFFFF");
        verify(gui).drawTextInGame(new Position(100, 14), "0", "#FFFFFF");
        verifyNoMoreInteractions(gui);
    }

    @Test
    void drawElementsHandlesException() {
        when(timer.getTimeLeft()).thenThrow(new RuntimeException("Timer error"));

        assertThrows(RuntimeException.class, () -> timerViewer.drawElements(gui));
    }
}