package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest{
    @Test
    void testTileInitialization(){
        Position screenPosition = new Position(2, 3);
        Position gridCoordinates = new Position(1, 4);
        Tile tile = new Tile("jewel", "ruby", screenPosition, gridCoordinates);

        assertEquals("jewel", tile.getType());
        assertEquals("#ff1c20", tile.getColor());
        assertEquals("◼", tile.getSymbol());
        assertEquals(gridCoordinates, tile.getGridCoordinates());
        assertEquals(screenPosition, tile.getScreenPosition());
        assertFalse(tile.isCursorOn());
    }

    @Test
    void testSetterAndCursor(){
        Tile tile = new Tile("empty", "default", new Position(0, 0), new Position(0, 0));
        tile.setType("bomb");
        tile.setColor("emerald");
        tile.setSymbol("⊖");

        assertEquals("bomb", tile.getType());
        assertEquals("#11a62f", tile.getColor());
        assertEquals("⊖", tile.getSymbol());
        tile.setCursorOn(true);
        assertTrue(tile.isCursorOn());
    }
}