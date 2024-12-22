package model;

import com.ldts.crystalclash.model.EmptyTile;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Color;
import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.EmptyTileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmptyTileTest {

    private EmptyTile emptyTile;

    @BeforeEach
    void setUp() {
        Position screenPosition = new Position(0, 0);
        Position gridCoordinates = new Position(1, 1);
        emptyTile = new EmptyTile(screenPosition, gridCoordinates);
    }

    @Test
    public void testEmptyTileInitialization() {
        Position screenPosition = new Position(0, 0);
        Position gridCoordinates = new Position(0, 0);

        EmptyTile tile = new EmptyTile(screenPosition, gridCoordinates, Color.DEFAULT);

        assertNotNull(tile.getBehaviorContext(), "BehaviorContext should not be null");

        assertNotNull(tile.getBehaviorContext().getBehavior(), "Tile behavior should be set.");
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

    @Test
    public void testBehaviorContext() {
        Position screenPosition = new Position(0, 0);
        Position gridCoordinates = new Position(0, 0);

        // Create the EmptyTile object
        EmptyTile tile = new EmptyTile(screenPosition, gridCoordinates);  // Make sure this constructor is used

        // Ensure the behaviorContext is not null
        assertNotNull(tile.getBehaviorContext(), "BehaviorContext should not be null");

        // Additionally, check if the behavior is set properly
        assertNotNull(tile.getBehaviorContext().getBehavior(), "Tile behavior should be set.");
    }

}
