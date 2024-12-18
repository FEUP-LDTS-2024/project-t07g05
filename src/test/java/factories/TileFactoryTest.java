package factories;

import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TileFactoryTest {
    private TileFactory tileFactory;
    private Position screenPosition;
    private Position gridCoordinates;

    @BeforeEach
    void setUp() {
        tileFactory = new TileFactory();
        screenPosition = mock(Position.class);
        gridCoordinates = mock(Position.class);
    }

    @Test
    void testCreateTileBomb() {

        Tile tile = tileFactory.createTile("bomb", screenPosition, gridCoordinates);
        assertInstanceOf(BombTile.class, tile, "Tile should be an instance of BombTile");
    }

    @Test
    void testCreateTileGem() {

        Tile tile = tileFactory.createTile("gem", screenPosition, gridCoordinates);
        assertInstanceOf(GemTile.class, tile, "Tile should be an instance of GemTile");
    }

    @Test
    void testCreateTileEmpty() {

        Tile tile = tileFactory.createTile("empty", screenPosition, gridCoordinates);
        assertInstanceOf(EmptyTile.class, tile, "Tile should be an instance of EmptyTile");
    }

    @Test
    void testCreateTileInvalidType() {

        assertThrows(IllegalArgumentException.class, () -> tileFactory.createTile("invalid", screenPosition, gridCoordinates));
    }

    @Test
    void testCreateRandomTile() {

        Tile tile = tileFactory.createRandomTile(screenPosition, gridCoordinates);
        assertTrue(tile instanceof BombTile || tile instanceof GemTile, "Tile should be either a BombTile or GemTile");
    }

    @Test
    void testGetRandomGemColor() {

        Color gemColor = tileFactory.getRandomGemColor();
        assertTrue(List.of(Color.DIAMOND, Color.EMERALD, Color.RUBY, Color.SAPPHIRE, Color.AMETHYST).contains(gemColor),
                "Gem color should be one of the predefined colors");
    }
}
