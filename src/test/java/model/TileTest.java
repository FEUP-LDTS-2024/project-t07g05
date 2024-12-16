package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TileTest{
    private Tile tile;
    private Position screenPosition;
    private Position gridPosition;

    @BeforeEach
    void setUp(){
        screenPosition = new Position(0, 0);
        gridPosition = new Position(0, 0);
        tile = new Tile("jewel", "ruby", screenPosition, gridPosition);
    }

    @Test
    void testTileInitialization(){
        assertNotNull(tile, "Tile wasn't initialized correctly");
    }

    @Test
    void testGetType(){
        assertEquals("jewel", tile.getType(), "Tile type wasn't correct");
    }

    @Test
    void testGetColor(){
        assertEquals("#ff1c20", tile.getColor(), "Tile color wasn't correct");
    }


    @Test
    void testGetSymbol(){
        assertEquals("◼", tile.getSymbol(), "Tile symbol wasn't correct");
    }

    @Test
    void testGetScreenPosition(){
        assertEquals(screenPosition, tile.getScreenPosition(), "Screen position wasn't correct");
    }

    @Test
    void testGetGridCoordinates(){
        assertEquals(gridPosition, tile.getGridCoordinates(), "Grid Coordinates weren't correct");
    }

    @Test
    void testCursorOn(){
        assertFalse(tile.isCursorOn(), "Cursor should be off");
        tile.setCursorOn(true);
        assertTrue(tile.isCursorOn(), "Cursor should be on");
    }
}