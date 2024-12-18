package controller;

import com.ldts.crystalclash.controller.TileController;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;


class TileControllerTest {
    private TileController controller;
    private Board board;
    private Game game;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        gui = mock(LanternaGUI.class);
        game = mock(Game.class);

        controller = new TileController(board);
    }

    @Test
    void testStep() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.LEFT);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.LEFT, 0));

        verify(board, atLeastOnce()).getCurrentTile();
        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testStepUpAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.UP, 0));

        verify(board, atLeastOnce()).getCurrentTile();
        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testStepRightAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.RIGHT);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.RIGHT, 0));

        verify(board, atLeastOnce()).getCurrentTile();
        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testStepDownAction() throws IOException {
        when(gui.getNextAction()).thenReturn(GUI.ACTION.DOWN);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.DOWN, 0));

        verify(board, atLeastOnce()).getCurrentTile();
        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testStepInvalidAction() throws IOException {
        when(gui.getNextAction()).thenReturn(null);

        assertDoesNotThrow(() -> controller.step(game, null, 0));

        verify(board, never()).getCurrentTile();
        verify(board, never()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testTileMovement() throws IOException {
        Tile tile = mock(Tile.class);
        when(board.getCurrentTile()).thenReturn(tile);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.LEFT);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.LEFT, 0));

        verify(tile).setCursorOn(true);
    }

    @Test
    void testEdgeCaseTileMovement() throws IOException {
        Tile tile = mock(Tile.class);
        when(board.getTile(0, 0)).thenReturn(tile);
        when(board.getCurrentTile()).thenReturn(tile);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.UP, 0));

        verify(board, never()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testTileSelection() throws IOException {
        Tile tile = mock(Tile.class);
        when(board.getCurrentTile()).thenReturn(tile);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.SELECT);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.SELECT, 0));

        verify(tile).setCursorOn(true);
    }



}