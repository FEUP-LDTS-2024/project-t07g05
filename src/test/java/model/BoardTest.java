package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void testBoardInitialization(){
        Board board = new Board(5,5,800,600,10,10);
        assertEquals(5, board.getRows());
        assertEquals(5, board.getColumns());
        assertEquals(800, board.getWidth());
        assertEquals(600, board.getHeight());
        assertNotNull(board.getGrid());
        assertNotNull(board.getCurrentTile());
    }

    @Test
    void testTileAccess(){
        Board board = new Board(3,3,800,600,10,10);
        Tile tile = board.getTile(1,1);
        assertNotNull(tile);
        assertEquals(1, tile.getGridCoordinates().getX());
        assertEquals(1, tile.getGridCoordinates().getY());
    }

    @Test
    void testIsValidPosition(){
        Board board = new Board(4,4,800,600,10,10);
        assertTrue(board.isValidPosition(2,2));
        assertFalse(board.isValidPosition(5,5));
    }

    @Test
    void testCalculateScreenPosition(){
        Board board = new Board(5,5,800,600,10,10);
        Position gridPosition = new Position(2,3);
        Position screenPosition = board.calculateScreenPosition(gridPosition);

        assertEquals(3 * 10 + board.getStartX(), screenPosition.getX());
        assertEquals(2 * 10 + board.getStartY(), screenPosition.getY());
    }
}
