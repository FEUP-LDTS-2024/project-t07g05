package controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.mockito.Mockito.*;

public class BoardControllerTest {
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

        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);

        when(tile1.getGridCoordinates()).thenReturn(position1);
        when(tile2.getGridCoordinates()).thenReturn(position2);

        when(board.getTile(0,0)).thenReturn(tile1);
        when(board.getTile(1,1)).thenReturn(tile2);

        boardController.swapTiles(tile1, tile2);

        verify(tile1).setGridCoordinates(position2);
        verify(tile2).setGridCoordinates(position1);
        verify(board).setTile(0,0,tile2);
        verify(board).setTile(1,1,tile1);
    }

    @Test
    void testMove(){
        Tile old = mock(Tile.class);
        Tile current = mock(Tile.class);

        when(board.getCurrentTile()).thenReturn(old);
        when(board.getTile(1,1)).thenReturn(current);

        boardController.moveCurrentTile(1,1);
        verify(old).setCursorOn(false);
        verify(board).setCurrentTile(current);
    }

    @Test
    void testShift(){
        Tile empty = mock(EmptyTile.class);
        Tile filled = mock(Tile.class);

        when(board.getRows()).thenReturn(3);
        when(board.getColumns()).thenReturn(1);
        when(board.getTile(2,0)).thenReturn(empty);
        when(board.getTile(1,0)).thenReturn(filled);

        boardController.shiftTilesDown();
        verify(board).setTile(2,0,filled);

    }

    @Test
    void testRefill(){
        when(board.getRows()).thenReturn(2);
        when(board.getColumns()).thenReturn(2);
        when(board.getTile(0,0)).thenReturn(mock(EmptyTile.class));
        when(board.getTile(1,1)).thenReturn(mock(EmptyTile.class));

        boardController.refillBoard();
        verify(board, atLeastOnce()).setTile(anyInt(), anyInt(), any(Tile.class));

    }


    @Test
    void testStep() throws IOException {
        Tile currentTile = mock(Tile.class);
        Tile targetTile = mock(Tile.class);
        Position currentPosition = mock(Position.class);
        Position targetPosition = mock(Position.class);


        when(currentTile.getGridCoordinates()).thenReturn(currentPosition);
        when(targetTile.getGridCoordinates()).thenReturn(targetPosition);
        when(currentPosition.getX()).thenReturn(0);
        when(currentPosition.getY()).thenReturn(0);
        when(targetPosition.getX()).thenReturn(0);
        when(targetPosition.getY()).thenReturn(1);


        when(board.getCurrentTile()).thenReturn(currentTile);
        when(board.getTile(0, 1)).thenReturn(targetTile);

        GameController spyController = spy(controller);
        doReturn(board).when(spyController).getModel();

        spyController.step(mock(Game.class), GUI.ACTION.UP);


        verify(board).getTile(0, 1);
        verify(board).setCurrentTile(targetTile);
        verify(currentTile).setCursorOn(false);
        verify(targetTile).setCursorOn(true);
    }

}
