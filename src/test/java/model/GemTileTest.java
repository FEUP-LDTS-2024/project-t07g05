package model;

import com.ldts.crystalclash.model.Color;
import com.ldts.crystalclash.model.GemTile;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.GemTileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GemTileTest {

    private GemTile gemTile;
    private Position screenPosition;
    private Position gridCoordinates;
    private Color color;

    @BeforeEach
    void setUp() {
        screenPosition = new Position(0, 0);
        gridCoordinates = new Position(1, 1);

        color = Color.DIAMOND;
        gemTile = new GemTile(screenPosition, gridCoordinates, color);
    }

    @Test
    void testGemTileInitialization() {
        assertNotNull(gemTile);
        assertEquals("*", gemTile.getSymbol());
        assertEquals(screenPosition, gemTile.getScreenPosition());
        assertEquals(gridCoordinates, gemTile.getGridCoordinates());
        assertEquals(color.getHexCode(), gemTile.getColor());
        assertNotNull(gemTile.getBehaviorContext());
    }

    @Test
    void testSetColor() {
        gemTile.setColor(Color.SAPPHIRE);
        assertEquals(Color.SAPPHIRE.getHexCode(), gemTile.getColor());
        assertEquals(Color.SAPPHIRE.getRarity(), gemTile.getColorRarity());
    }

    @Test
    void testSetCursorOn() {
        gemTile.setCursorOn(true);
        assertTrue(gemTile.isCursorOn());

        gemTile.setCursorOn(false);
        assertFalse(gemTile.isCursorOn());
    }

    @Test
    void testBehaviorContextInitialization() {
        BehaviorContext behaviorContext = gemTile.getBehaviorContext();
        assertNotNull(behaviorContext);
        assertInstanceOf(GemTileBehavior.class, behaviorContext.getBehavior());
    }

    @Test
    void testPositionSetters() {
        // Test setting new positions
        Position newScreenPosition = new Position(10, 10);
        Position newGridCoordinates = new Position(2, 2);

        gemTile.setScreenPosition(newScreenPosition);
        gemTile.setGridCoordinates(newGridCoordinates);

        assertEquals(newScreenPosition, gemTile.getScreenPosition());
        assertEquals(newGridCoordinates, gemTile.getGridCoordinates());
    }
}
