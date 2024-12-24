package com.ldts.crystalclash.viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;


class InstructionsViewerTest {

    private InstructionsViewer viewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        Instructions instructions = new Instructions();
        viewer = new InstructionsViewer(instructions);
        gui = mock(GUI.class);
    }

    @Test
    void drawGameBackgroundCalled() {
        viewer.drawElements(gui);
        verify(gui, times(1)).drawGameBackground(120, 40);
    }

    @Test
    void drawTitleCalledCorrectly() {
        viewer.drawElements(gui);
        verify(gui, times(1)).drawText(new Position(50, 0), "INSTRUCTIONS", "#ffff00");
    }

    @Test
    void drawInstructionsText() {
        viewer.drawElements(gui);

        verify(gui, atLeastOnce()).drawText(new Position(1, 1), "Welcome to Crystal Clash!", "#ffffff");
        verify(gui, atLeastOnce()).drawText(new Position(1, 3), "1. The game board is an 8x8 grid of crystals.", "#ffffff");
        verify(gui, atLeastOnce()).drawText(new Position(1, 9), "   column to score points.", "#ffffff");
        verify(gui, atLeastOnce()).drawText(new Position(42, 38), "click 'q' to return to the menu", "#ff3300");
    }

    @Test
    void drawPointValues() {
        viewer.drawElements(gui);

        verify(gui).drawText(new Position(3, 20), "   - DIAMOND --> 5 points", "#00ffff");
        verify(gui).drawText(new Position(3, 21), "   - EMERALD --> 4 points", "#00ff00");
        verify(gui).drawText(new Position(3, 24), "   - AMETHYST --> 1", "#ff00ff");
        verify(gui).drawText(new Position(3, 25), "   - DEFAULT --> 1", "#aaaaaa");
    }

    @Test
    void drawHowToPlayText() {
        viewer.drawElements(gui);

        verify(gui).drawText(new Position(83, 1), "HOW TO PLAY? ", "#00d327");
        verify(gui).drawText(new Position(59, 3), "Press 'space' to SELECT the tile you want to change position", "#ffffff");
        verify(gui).drawText(new Position(60, 7), "'Arrow Up'    -  change with the adjacent tile on top", "#ffffff");
        verify(gui).drawText(new Position(60, 13), "'Arrow Right' -  change with the adjacent tile on the right", "#ffffff");
    }

    @Test
    void drawBombsInstructions() {
        viewer.drawElements(gui);

        verify(gui).drawText(new Position(1, 15), "   - Bombs are special tiles that, when matched", "#ffffff");
        verify(gui).drawText(new Position(1, 34), "    -[number of consecutive bombs] + [sum of all adjacent tiles]", "#ffffff");
    }
}
