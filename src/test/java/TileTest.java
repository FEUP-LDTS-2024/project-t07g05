import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TileTest {

    private Tile tile;

    @BeforeEach
    public void setUp(){

        Position screenPosition = new Position(0,0);
        Position gridCoordinates = new Position(0,0);

        tile = new Tile("jewel", "ruby", screenPosition, gridCoordinates);
    }


    @Test
    public void testCursorOn(){

        tile.setCursorOn(true);
        Assertions.assertTrue(tile.isCursorOn(), "The cursor should be on");

        tile.setCursorOn(false);
        Assertions.assertFalse(tile.isCursorOn(), "The cursor should be off");
    }


    @Test
    public void testColorChange(){

        tile.setColor("#11a62f");

        Assertions.assertEquals("#11a62f", tile.getColor(), "The tile color should be updated to the specific color.");
    }


    @Test
    public void testTileInitialization(){

        Assertions.assertNotNull(tile, "Tile should be initialized.");

        Assertions.assertEquals("ruby", tile.getColor(), "The tile color should be ruby.");

        Assertions.assertEquals("◼", tile.getSymbol(), "The tile should be ◼.");
    }


    @Test
    public void testSymbolDetermination(){

        Tile bombTile = new Tile("bomb", "emerald", new Position(1,1), new Position(1,1));
        Assertions.assertEquals("⊖", bombTile.getSymbol(), "Tile symbol should be ⊖ for bombs");

        Tile defaultTile = new Tile("default", "ruby", new Position(0, 0), new Position(0, 0));
        Assertions.assertEquals("◼", defaultTile.getSymbol(), "The tile symbol should be ◼ for default tiles.");
    }
}
