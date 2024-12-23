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

class TileBehaviorTest {

    private TileBehavior bombTileBehavior;
    private TileBehavior emptyTileBehavior;
    private TileBehavior gemTileBehavior;

    private Tile mockTile;
    private Board mockBoard;

    @BeforeEach
    void setUp() {
        bombTileBehavior = new BombTileBehavior();
        emptyTileBehavior = new EmptyTileBehavior();
        gemTileBehavior = new GemTileBehavior();

        mockTile = mock(Tile.class);
        mockBoard = mock(Board.class);
        Position mockPosition = mock(Position.class);

        when(mockTile.getGridCoordinates()).thenReturn(mockPosition);
        when(mockPosition.getX()).thenReturn(1);
        when(mockPosition.getY()).thenReturn(1);
    }

    @Test
    void testBombTileBehaviorCalculatePoints() {
        when(mockTile.getColorRarity()).thenReturn(5);

        Tile mockAdjacentTile1 = mock(Tile.class);
        when(mockAdjacentTile1.getColorRarity()).thenReturn(1);

        Tile mockAdjacentTile2 = mock(Tile.class);
        when(mockAdjacentTile2.getColorRarity()).thenReturn(2);

        when(mockBoard.getTile(0, 1)).thenReturn(mockAdjacentTile1);
        when(mockBoard.getTile(2, 1)).thenReturn(mockAdjacentTile2);
        when(mockBoard.isValidPosition(0, 1)).thenReturn(true);
        when(mockBoard.isValidPosition(2, 1)).thenReturn(true);

        int points = bombTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(3, points, "BombTileBehavior should return the correct points.");
    }



    @Test
    void testBombTileBehaviorPopOff() {
        Set<Tile> toRemove = new HashSet<>();

        bombTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertDoesNotThrow(() -> bombTileBehavior.popOff(mockTile, mockBoard, toRemove));
    }

    @Test
    void testEmptyTileBehaviorPopOff() {
        Set<Tile> toRemove = new HashSet<>();

        emptyTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.isEmpty(), "EmptyTileBehavior should not add anything to the toRemove set.");
    }

    @Test
    void testGemTileBehaviorPopOff() {
        Set<Tile> toRemove = new HashSet<>();

        gemTileBehavior.popOff(mockTile, mockBoard, toRemove);

        assertTrue(toRemove.contains(mockTile), "GemTileBehavior should add the tile to the toRemove set.");
    }

    @Test
    void testEmptyTileBehaviorCalculatePoints() {
        int points = emptyTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(0, points, "EmptyTileBehavior should return 0 points.");
    }

    @Test
    void testGemTileBehaviorCalculatePoints() {
        when(mockTile.getColorRarity()).thenReturn(3);

        int points = gemTileBehavior.calculatePoints(mockTile, mockBoard);

        assertEquals(3, points, "GemTileBehavior should return the tile's rarity as points.");
    }
}
