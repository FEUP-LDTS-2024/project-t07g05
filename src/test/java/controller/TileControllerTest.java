package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Score;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TileControllerTest {

    private TileController controller;
    private Board board;
    private Tile currentTile;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        currentTile = mock(Tile.class);

        when(board.getCurrentTile()).thenReturn(currentTile);
        controller = new TileController(board);
    }

    @Test
    void testStepLeftAction() {
        Tile leftTile = mock(Tile.class);
        when(board.getTileToTheLeft(currentTile)).thenReturn(leftTile);

        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.LEFT, 0));

        verify(currentTile).setCursorOn(false);
        verify(leftTile).setCursorOn(true);
        verify(board).setCurrentTile(leftTile);
    }

    @Test
    void testStepRightAction() {
        Tile rightTile = mock(Tile.class);
        when(board.getTileToTheRight(currentTile)).thenReturn(rightTile);

        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.RIGHT, 0));

        verify(currentTile).setCursorOn(false);
        verify(rightTile).setCursorOn(true);
        verify(board).setCurrentTile(rightTile);
    }

    @Test
    void testStepUpAction() {
        Tile topTile = mock(Tile.class);
        when(board.getTileOnTop(currentTile)).thenReturn(topTile);

        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.UP, 0));

        verify(currentTile).setCursorOn(false);
        verify(topTile).setCursorOn(true);
        verify(board).setCurrentTile(topTile);
    }

    @Test
    void testStepDownAction() {
        Tile bottomTile = mock(Tile.class);
        when(board.getTileOnBottom(currentTile)).thenReturn(bottomTile);

        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.DOWN, 0));

        verify(currentTile).setCursorOn(false);
        verify(bottomTile).setCursorOn(true);
        verify(board).setCurrentTile(bottomTile);
    }

    @Test
    void testStepSelectAction() {
        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.SELECT, 0));

        verify(currentTile).setCursorOn(true);
        verifyNoMoreInteractions(currentTile);
    }

    @Test
    void testStepInvalidAction() {
        assertDoesNotThrow(() -> controller.step(mock(Game.class), null, 0));

        verifyNoInteractions(currentTile);
        verify(board, never()).setCurrentTile(any());
    }

    @Test
    void testEdgeCaseNoTileToTheLeft() {
        when(board.getTileToTheLeft(currentTile)).thenReturn(null);

        assertDoesNotThrow(() -> controller.step(mock(Game.class), GUI.ACTION.LEFT, 0));

        verify(currentTile, never()).setCursorOn(false);
        verify(board, never()).setCurrentTile(any());
    }

    @AfterEach
    void tearDown() {
        controller = null;
        board = null;
        currentTile = null;
    }

}
