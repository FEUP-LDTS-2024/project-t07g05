package com.ldts.crystalclash.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EmptyTileTest {
    private EmptyTile emptyTile;

    @BeforeEach
    void setUp() {
        Position screenPosition = new Position(0, 0);
        Position gridCoordinates = new Position(1, 1);
        emptyTile = new EmptyTile(screenPosition, gridCoordinates, Color.DEFAULT);
    }

    @Test
    void testEmptyTileInitialization() {
        assertNotNull(emptyTile.getBehaviorContext(), "BehaviorContext should not be null");
        assertNotNull(emptyTile.getBehaviorContext().getBehavior(), "Tile behavior should be set.");
    }

    @Test
    void testDefaultConstructorBehavior() {
        EmptyTile tile = new EmptyTile(new Position(0, 0), new Position(1, 1));
        assertNull(tile.getBehaviorContext(), "BehaviorContext should be null for default constructor.");
    }

    @Test
    void testSetColor() {
        emptyTile.setColor(Color.SAPPHIRE);
        assertEquals("#0e3edd", emptyTile.getColor());
        assertEquals(2, emptyTile.getColorRarity());
    }

    @Test
    void testSetCursorOn() {
        emptyTile.setCursorOn(true);
        assertTrue(emptyTile.isCursorOn());
        emptyTile.setCursorOn(false);
        assertFalse(emptyTile.isCursorOn());
    }

    @Test
    void testPositionSetters() {
        Position newScreenPosition = new Position(10, 10);
        Position newGridCoordinates = new Position(2, 2);

        emptyTile.setScreenPosition(newScreenPosition);
        emptyTile.setGridCoordinates(newGridCoordinates);

        assertEquals(newScreenPosition, emptyTile.getScreenPosition());
        assertEquals(newGridCoordinates, emptyTile.getGridCoordinates());
    }
}
