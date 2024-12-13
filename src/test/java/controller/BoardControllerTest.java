package controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
class BoardControllerTest {
    private BoardController boardController;
    private Board board;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        boardController = new BoardController(board);
    }

    @Test
    void testSwapTiles(){
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);

        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,1);

        when(tile1.getGridCoordinates()).thenReturn(pos1);
        when(tile2.getGridCoordinates()).thenReturn(pos2);
        when(board.getTile(0,0)).thenReturn(tile1);
        when(board.getTile(1,0)).thenReturn(tile2);

        boardController.swapTiles(tile1, tile2);

        verify(board).setTile(0,0,tile2);
        verify(board).setTile(1,1,tile1);
        verify(tile1).setGridCoordinates(pos2);
        verify(tile2).setGridCoordinates(pos1);
    }

    @Test
    void testMoveCurrentTile(){
        Tile currentTile = mock(Tile.class);
        Tile newTile = mock(Tile.class);
        when(board.isValidPosition(1,1)).thenReturn(true);
        when(board.getTile(1,1)).thenReturn(newTile);
        when(board.getCurrentTile()).thenReturn(currentTile);

        verify(currentTile).setCursorOn(false);
        verify(newTile).setCursorOn(true);
        verify(board).setCurrentTile(newTile);
    }

    @Test
    void testShiftTilesDown() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Tile emptyTile = mock(EmptyTile.class);
        Tile nonEmptyTile = mock(Tile.class);

        when(board.getTile(4, 0)).thenReturn(emptyTile);
        when(board.getTile(3, 0)).thenReturn(nonEmptyTile);

        boardController.shiftTilesDown();

        verify(board).setTile(4, 0, nonEmptyTile);
        verify(board).setTile(3, 0, emptyTile);
    }

    @Test
    void testRefillBoard() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Tile tile = mock(Tile.class);
                when(board.getTile(row, col)).thenReturn(tile);
                if (row == 2 && col == 2) {
                    when(tile instanceof EmptyTile).thenReturn(true);
                }
            }
        }

        boardController.refillBoard();


        verify(board,atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));
    }

    @Test
    void testStep() throws IOException {
        Tile currentTile = mock(Tile.class);
        Position currentPosition = new Position(2, 2);

        when(board.getCurrentTile()).thenReturn(currentTile);
        when(currentTile.getGridCoordinates()).thenReturn(currentPosition);
        when(board.isValidPosition(anyInt(), anyInt())).thenReturn(true);

        boardController.step(mock(Game.class), GUI.ACTION.UP);
        verify(board).getTile(1, 2);
    }
}

