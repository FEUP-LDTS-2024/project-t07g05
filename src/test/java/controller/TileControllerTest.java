package controller;

import com.ldts.crystalclash.controller.TileController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;


class TileControllerTest {
    private TileController controller;
    private Board board;
    private Game game;
    private Tile currentTile;


    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        game = mock(Game.class);
        currentTile = mock(Tile.class);

        when(board.getCurrentTile()).thenReturn(currentTile);
        controller = new TileController(board);
    }

    @Test
    void testStepLeftAction() {
        Tile leftTile = mock(Tile.class);
        when(board.getTileToTheLeft(currentTile)).thenReturn(leftTile);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.LEFT, 0));

        verify(currentTile).setCursorOn(false);
        verify(leftTile).setCursorOn(true);
        verify(board).setCurrentTile(leftTile);
    }

    @Test
    void testStepRightAction() {
        Tile rightTile = mock(Tile.class);
        when(board.getTileToTheRight(currentTile)).thenReturn(rightTile);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.RIGHT, 0));

        verify(currentTile).setCursorOn(false);
        verify(rightTile).setCursorOn(true);
        verify(board).setCurrentTile(rightTile);
    }

    @Test
    void testStepUpAction() {
        Tile topTile = mock(Tile.class);
        when(board.getTileOnTop(currentTile)).thenReturn(topTile);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.UP, 0));

        verify(currentTile).setCursorOn(false);
        verify(topTile).setCursorOn(true);
        verify(board).setCurrentTile(topTile);
    }

    @Test
    void testStepDownAction() {
        Tile bottomTile = mock(Tile.class);
        when(board.getTileOnBottom(currentTile)).thenReturn(bottomTile);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.DOWN, 0));

        verify(currentTile).setCursorOn(false);
        verify(bottomTile).setCursorOn(true);
        verify(board).setCurrentTile(bottomTile);
    }

    @Test
    void testStepInvalidAction() {
        assertDoesNotThrow(() -> controller.step(game, null, 0));

        verifyNoInteractions(currentTile);
        verify(board, never()).setCurrentTile(any());
    }

    @Test
    void testEdgeCaseNoTileToTheLeft() {
        when(board.getTileToTheLeft(currentTile)).thenReturn(null);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.LEFT, 0));

        verify(currentTile, never()).setCursorOn(false);
        verify(board, never()).setCurrentTile(any());
    }

    @Test
    void testTileSelection() {
        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.SELECT, 0));

        verify(currentTile).setCursorOn(true);
        verifyNoMoreInteractions(currentTile); // No movement should occur on selection
    }

    @Test
    void testCursorStateConsistency() {
        Tile targetTile = mock(Tile.class);
        when(board.getTileOnTop(currentTile)).thenReturn(targetTile);

        assertDoesNotThrow(() -> controller.step(game, GUI.ACTION.UP, 0));

        verify(currentTile).setCursorOn(false);
        verify(targetTile).setCursorOn(true);
    }
}