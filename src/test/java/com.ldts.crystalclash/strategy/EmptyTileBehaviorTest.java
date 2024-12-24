package com.ldts.crystalclash.strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmptyTileBehaviorTest {

    private EmptyTileBehavior emptyTileBehavior;
    private Tile mockTile;
    private Board mockBoard;

    @BeforeEach
    void setUp() {
        emptyTileBehavior = new EmptyTileBehavior();
        mockTile = mock(Tile.class);
        mockBoard = mock(Board.class);
    }

    @Test
    void testPopOffDoesNothing() {
        Set<Tile> toRemove = new HashSet<>();

        emptyTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.isEmpty(), "The toRemove set should remain empty.");
    }

    @Test
    void testCalculatePointsReturnsZero() {
        int points = emptyTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(0, points, "Points for an empty tile should always be 0.");
    }
}

