package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BombTileBehaviorTest {

    private BombTileBehavior bombTileBehavior;
    private Board mockBoard;
    private Tile mockTile;

    @BeforeEach
    void setUp() {
        bombTileBehavior = new BombTileBehavior();
        mockBoard = mock(Board.class);
        mockTile = mock(Tile.class);

        when(mockTile.getGridCoordinates()).thenReturn(new Position(2, 2));
    }

    @Test
    void testPopOffAddsAdjacentTiles() {
        Set<Tile> toRemove = new HashSet<>();
        Tile adjacentTile1 = mock(Tile.class);
        Tile adjacentTile2 = mock(Tile.class);

        when(mockBoard.isValidPosition(1, 2)).thenReturn(true);
        when(mockBoard.isValidPosition(3, 2)).thenReturn(true);

        when(mockBoard.getTile(1, 2)).thenReturn(adjacentTile1);
        when(mockBoard.getTile(3, 2)).thenReturn(adjacentTile2);

        bombTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.contains(adjacentTile1));
        assertTrue(toRemove.contains(adjacentTile2));
        assertEquals(2, toRemove.size(), "Only two adjacent tiles should be added to the set.");
    }

    @Test
    void testPopOffIgnoresInvalidAndEmptyTiles() {
        Set<Tile> toRemove = new HashSet<>();

        when(mockBoard.isValidPosition(1, 2)).thenReturn(false);
        when(mockBoard.isValidPosition(3, 2)).thenReturn(true);
        when(mockBoard.getTile(3, 2)).thenReturn(null); // Empty tile

        bombTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.isEmpty(), "No tiles should be added for invalid or empty positions.");
    }

    @Test
    void testCalculatePointsForAdjacentTiles() {
        Tile adjacentTile1 = mock(Tile.class);
        Tile adjacentTile2 = mock(Tile.class);

        when(mockBoard.isValidPosition(1, 2)).thenReturn(true);
        when(mockBoard.isValidPosition(3, 2)).thenReturn(true);

        when(mockBoard.getTile(1, 2)).thenReturn(adjacentTile1);
        when(mockBoard.getTile(3, 2)).thenReturn(adjacentTile2);
        when(adjacentTile1.getColorRarity()).thenReturn(3);
        when(adjacentTile2.getColorRarity()).thenReturn(2);

        int points = bombTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(5, points, "Points should be the sum of rarities of adjacent tiles.");
    }

    @Test
    void testCalculatePointsIgnoresInvalidAndEmptyTiles() {
        Tile adjacentTile = mock(Tile.class);

        when(mockBoard.isValidPosition(1, 2)).thenReturn(true);
        when(mockBoard.isValidPosition(3, 2)).thenReturn(false);

        when(mockBoard.getTile(1, 2)).thenReturn(adjacentTile);
        when(adjacentTile.getColorRarity()).thenReturn(3);

        int points = bombTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(3, points, "Only valid tiles should contribute to the points.");
    }

    @Test
    void testCalculatePointsReturnsZeroForNoValidTiles() {
        when(mockBoard.isValidPosition(anyInt(), anyInt())).thenReturn(false);

        int points = bombTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(0, points, "Points should be zero when there are no valid adjacent tiles.");
    }
}
