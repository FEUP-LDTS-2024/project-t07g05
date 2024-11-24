package controller;

import model.Board;
import model.Tile;
import model.Position;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class BoardControllerTest {
    private BoardController boardController;
    private Board mockBoard;

    @BeforeEach
    void setUp() {
        mockBoard = Mockito.mock(Board.class);
        boardController = new BoardController(mockBoard);
    }

    @Test
    void testSwapTiles() {
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);

        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);

        when(tile1.getGridCoordinates()).thenReturn(position1);
        when(tile2.getGridCoordinates()).thenReturn(position2);

        when(mockBoard.getTile(0,0)).thenReturn(tile1);
        when(mockBoard.getTile(1,1)).thenReturn(tile2);

        boardController.swapTiles(tile1, tile2);

        verify(tile1).setGridCoordinates(position2);
        verify(tile2).setGridCoordinates(position1);
        verify(mockBoard).setTile(0,0, tile2);
        verify(mockBoard).setTile(1,1, tile1);
    }

}
