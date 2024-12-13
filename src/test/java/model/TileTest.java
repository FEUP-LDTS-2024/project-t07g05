package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void testInvalidColorDefaultsToWhite() {
        Tile invalidColorTile = new Tile("jewel", "unknown", screenPosition, gridPosition);
        assertEquals("#f3fafb", invalidColorTile.getColor(), "Unknown colors should default to white");
    }


    @Test
    void testInvalidTypeDefaultsToDefaultSymbol() {
        Tile invalidTypeTile = new Tile("unknown", "ruby", screenPosition, gridPosition);
        assertEquals("◼", invalidTypeTile.getSymbol(), "Unknown types should default to the default symbol");
    }


    @Test
    void testSetType() {
        tile.setType("bomb");
        assertEquals("bomb", tile.getType(), "Type should be updated correctly");
    }

    @Test
    void testSetColor() {
        tile.setColor("#123456");
        assertEquals("#123456", tile.getColor(), "Color should be updated correctly");
    }

    @Test
    void testSetScreenPosition() {
        Position newScreenPosition = mock(Position.class);
        tile.setScreenPosition(newScreenPosition);
        assertEquals(newScreenPosition, tile.getScreenPosition(), "Screen position should be updated correctly");
    }

    @Test
    void testSetGridCoordinates() {
        Position newGridPosition = mock(Position.class);
        tile.setGridCoordinates(newGridPosition);
        assertEquals(newGridPosition, tile.getGridCoordinates(), "Grid coordinates should be updated correctly");
    }


    @Test
    void testSetSymbol() {
        tile.setSymbol("X");
        assertEquals("X", tile.getSymbol(), "Symbol should be updated correctly");
    }
}