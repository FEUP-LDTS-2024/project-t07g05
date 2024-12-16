package model;

import com.ldts.crystalclash.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(5,5,100,100,10,10);
    }

    @Test
    void testGetGrid(){
        assertNotNull(board.getGrid(), "Grid cannot be null");
    }

    @Test
    void testGetTile(){
        Tile tile = board.getTile(0,0);
        assertNotNull(tile, "Tile at position (0,0) cannot be null");
    }

    @Test
    void testSetTile(){
        Tile newTile = mock(Tile.class);
        board.setTile(0,0,newTile);
        assertEquals(newTile,board.getTile(0,0), "Tile was not set correctly");
    }

    @Test
    void testInvalidPosition() {
        Tile tile = board.getTile(10, 10); // Fora dos limites
        assertNull(tile, "Tile at position (10,10) should be null for invalid positions");
    }


    @Test
    void initializeBoard(){
        Tile tile = board.getTile(0,0);
        assertNotNull(tile, "Tile at position (0,0) should be valid");
    }
}
