package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GemTileBehaviorTest {

    private GemTileBehavior gemTileBehavior;
    private Tile mockTile;
    private Board mockBoard;

    @BeforeEach
    void setUp() {
        gemTileBehavior = new GemTileBehavior();
        mockTile = mock(Tile.class);
        mockBoard = mock(Board.class);
    }

    @Test
    void testPopOffAddsTileToRemoveSet() {
        Set<Tile> toRemove = new HashSet<>();

        gemTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.contains(mockTile), "The toRemove set should contain the mockTile.");
        assertEquals(1, toRemove.size(), "The toRemove set should contain exactly one tile.");
    }

    @Test
    void testCalculatePointsReturnsTileRarity() {
        when(mockTile.getColorRarity()).thenReturn(5);

        int points = gemTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(5, points, "The calculatePoints method should return the tile's rarity.");
    }
}

