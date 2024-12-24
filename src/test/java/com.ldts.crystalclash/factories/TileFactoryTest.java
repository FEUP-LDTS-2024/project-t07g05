package com.ldts.crystalclash.factories;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

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
    void testGetRandomGemColor() {
        List<Color> encounteredColors = new ArrayList<>();
        for (int i = 0; i < 1000; i++) { // Increase iterations to capture randomness better
            Color gemColor = tileFactory.getRandomGemColor();
            if (!encounteredColors.contains(gemColor)) {
                encounteredColors.add(gemColor);
            }
        }

        List<Color> expectedColors = List.of(Color.DIAMOND, Color.EMERALD, Color.RUBY, Color.SAPPHIRE, Color.AMETHYST);
        assertTrue(encounteredColors.containsAll(expectedColors),
                "Random gem colors should include all predefined colors");
        assertEquals(expectedColors.size(), encounteredColors.size(),
                "All expected colors should be encountered");
    }

    @Test
    void testCreateTileCaseInsensitive() {
        Tile bombTile = tileFactory.createTile("BoMb", screenPosition, gridCoordinates); // Mixed-case
        assertInstanceOf(BombTile.class, bombTile, "Tile creation should be case-insensitive");

        Tile gemTile = tileFactory.createTile("GEM", screenPosition, gridCoordinates); // Upper-case
        assertInstanceOf(GemTile.class, gemTile, "Tile creation should be case-insensitive");

        Tile emptyTile = tileFactory.createTile("empty", screenPosition, gridCoordinates); // Lower-case
        assertInstanceOf(EmptyTile.class, emptyTile, "Tile creation should work with lower-case");
    }


}
