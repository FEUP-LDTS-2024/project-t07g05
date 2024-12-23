package factories;

import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TileFactoryTest {
    private TileFactory tileFactory;
    private Position screenPosition;
    private Position gridCoordinates;

    @BeforeEach
    void setUp() {
        tileFactory = new TileFactory();
        screenPosition = new Position(100, 200);
        gridCoordinates = new Position(3, 4);
    }

    @Test
    void testCreateTileBomb() {
        Tile tile = tileFactory.createTile("bomb", screenPosition, gridCoordinates);
        assertInstanceOf(BombTile.class, tile, "Tile should be an instance of BombTile");
        assertEquals(screenPosition, tile.getScreenPosition(), "Screen position should match");
        assertEquals(gridCoordinates, tile.getGridCoordinates(), "Grid coordinates should match");
    }

    @Test
    void testCreateTileGem() {
        Tile tile = tileFactory.createTile("gem", screenPosition, gridCoordinates);
        assertInstanceOf(GemTile.class, tile, "Tile should be an instance of GemTile");

        String gemColorHex = ((GemTile) tile).getColor();

        assertTrue(
                List.of(Color.DIAMOND.getHexCode(), Color.EMERALD.getHexCode(), Color.RUBY.getHexCode(),
                        Color.SAPPHIRE.getHexCode(), Color.AMETHYST.getHexCode()).contains(gemColorHex),
                "Gem tile color should be valid"
        );
    }


    @Test
    void testCreateTileEmpty() {
        Tile tile = tileFactory.createTile("empty", screenPosition, gridCoordinates);
        assertInstanceOf(EmptyTile.class, tile, "Tile should be an instance of EmptyTile");
        assertEquals(screenPosition, tile.getScreenPosition(), "Screen position should match");
        assertEquals(gridCoordinates, tile.getGridCoordinates(), "Grid coordinates should match");
    }

    @Test
    void testCreateTileInvalidType() {
        assertThrows(IllegalArgumentException.class, () ->
                tileFactory.createTile("invalid", screenPosition, gridCoordinates));
    }

    @Test
    void testCreateRandomTile() {
        List<Class<?>> possibleTypes = new ArrayList<>();
        for (int i = 0; i < 100; i++) { // Test with enough iterations to cover randomness
            Tile tile = tileFactory.createRandomTile(screenPosition, gridCoordinates);
            if (tile instanceof BombTile) {
                possibleTypes.add(BombTile.class);
            } else if (tile instanceof GemTile) {
                possibleTypes.add(GemTile.class);
            }
        }

        assertTrue(possibleTypes.contains(BombTile.class), "Random tiles should include BombTile");
        assertTrue(possibleTypes.contains(GemTile.class), "Random tiles should include GemTile");
    }

    @Test
    void testGetRandomGemColor() {
        List<Color> encounteredColors = new ArrayList<>();
        for (int i = 0; i < 100; i++) { // Run multiple iterations to capture randomness
            Color gemColor = tileFactory.getRandomGemColor();
            encounteredColors.add(gemColor);
        }

        List<Color> expectedColors = List.of(Color.DIAMOND, Color.EMERALD, Color.RUBY, Color.SAPPHIRE, Color.AMETHYST);
        assertTrue(encounteredColors.containsAll(expectedColors),
                "Random gem colors should include all predefined colors");
    }
}
