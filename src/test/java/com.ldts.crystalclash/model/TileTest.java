package com.ldts.crystalclash.model;

import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.GemTileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    private Tile tile;
    private Position screenPosition;
    private Position gridCoordinates;
    private Color color;

    @BeforeEach
    void setUp() {
        screenPosition = new Position(0, 0);
        gridCoordinates = new Position(1, 1);
        color = Color.DIAMOND;
        tile = new GemTile(screenPosition, gridCoordinates, color);
    }

    @Test
    void testTileInitialization() {
        assertNotNull(tile);
        assertEquals("*", tile.getSymbol());
        assertEquals(screenPosition, tile.getScreenPosition());
        assertEquals(gridCoordinates, tile.getGridCoordinates());
        assertEquals(color.getHexCode(), tile.getColor());
        assertNotNull(tile.getBehaviorContext());
    }

    @Test
    void testSettersAndGetters() {
        Position newScreenPosition = new Position(10, 10);
        Position newGridCoordinates = new Position(2, 2);

        tile.setScreenPosition(newScreenPosition);
        tile.setGridCoordinates(newGridCoordinates);

        assertEquals(newScreenPosition, tile.getScreenPosition());
        assertEquals(newGridCoordinates, tile.getGridCoordinates());
    }

    @Test
    void testColorRarity() {
        assertEquals(5, tile.getColorRarity());
    }

    @Test
    void testCursorOn() {
        tile.setCursorOn(true);
        assertTrue(tile.isCursorOn());

        tile.setCursorOn(false);
        assertFalse(tile.isCursorOn());
    }

    @Test
    void testBehaviorContext() {
        BehaviorContext behaviorContext = tile.getBehaviorContext();
        assertNotNull(behaviorContext);
        assertInstanceOf(GemTileBehavior.class, behaviorContext.getBehavior());
    }

    @Test
    void testInvalidColor() {
        assertThrows(IllegalArgumentException.class, () -> new GemTile(screenPosition, gridCoordinates, Color.valueOf("INVALID_COLOR")));
    }
}