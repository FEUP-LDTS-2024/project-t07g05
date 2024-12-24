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
        for (int i = 0; i < 10000; i++) {  // Increase iterations for better randomness check
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
        Tile bombTile = tileFactory.createTile("BoMb", screenPosition, gridCoordinates);
        assertInstanceOf(BombTile.class, bombTile);

        Tile gemTile = tileFactory.createTile("GEM", screenPosition, gridCoordinates);
        assertInstanceOf(GemTile.class, gemTile);

        Tile emptyTile = tileFactory.createTile("empty", screenPosition, gridCoordinates);
        assertInstanceOf(EmptyTile.class, emptyTile);

        // Additional cases
        Tile lowerCaseBombTile = tileFactory.createTile("bomb", screenPosition, gridCoordinates);
        assertInstanceOf(BombTile.class, lowerCaseBombTile);

        Tile mixedCaseEmptyTile = tileFactory.createTile("EmPtY", screenPosition, gridCoordinates);
        assertInstanceOf(EmptyTile.class, mixedCaseEmptyTile);
    }


    @Test
    void testCreateRandomTile() {
        Position screenPosition = new Position(100, 200);
        Position gridCoordinates = new Position(3, 4);

        int bombCount = 0;
        int gemCount = 0;

        for (int i = 0; i < 1000; i++) {
            Tile tile = tileFactory.createRandomTile(screenPosition, gridCoordinates);
            if (tile instanceof BombTile) {
                bombCount++;
            } else if (tile instanceof GemTile) {
                gemCount++;
            }
        }

        assertTrue(bombCount > 0, "Bomb tiles should be created");
        assertTrue(gemCount > 0, "Gem tiles should be created");
    }

    @Test
    void testCreateTileNullType() {
        assertThrows(NullPointerException.class, () ->
                        tileFactory.createTile(null, screenPosition, gridCoordinates),
                "Creating a tile with null type should throw a NullPointerException");
    }


    @Test
    void testCreateTileNullPosition() {
        Tile tile = tileFactory.createTile("gem", null, gridCoordinates);
        assertNotNull(tile, "Tile should not be null");
    }


    @Test
    void testCreateTileNullReturn() {
        Tile tile = tileFactory.createTile("bomb", screenPosition, gridCoordinates);
        assertNotNull(tile, "Tile creation should not return null even with mutant code");
    }

    @Test
    void testCreateRandomTileNullReturn() {
        Tile tile = tileFactory.createRandomTile(screenPosition, gridCoordinates);
        assertNotNull(tile, "Random tile creation should not return null even with mutant code");
    }


}
