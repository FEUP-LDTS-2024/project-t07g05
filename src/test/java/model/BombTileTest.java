package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BombTileTest {

    private BombTile bombTile;
    private Board mockBoard;
    private Tile mockTileUp, mockTileDown, mockTileLeft, mockTileRight;
    private Set<Tile> toRemove;

    @BeforeEach
    void setUp() {
        // Mock board and initialize bombTile
        mockBoard = mock(Board.class);
        Position screenPosition = new Position(100, 100);
        Position gridCoordinates = new Position(2, 2);
        Color color = Color.DIAMOND;
        bombTile = new BombTile(screenPosition, gridCoordinates, color);

        // Initialize adjacent mock tiles
        mockTileUp = mock(Tile.class);
        mockTileDown = mock(Tile.class);
        mockTileLeft = mock(Tile.class);
        mockTileRight = mock(Tile.class);

        // Initialize the removal set
        toRemove = new HashSet<>();
    }

    @Test
    void testBombTileBehaviorRemovesAdjacentTiles() {
        // Setup mock board behavior for adjacent tiles
        Position bombPosition = bombTile.getGridCoordinates();
        when(mockBoard.getTile(bombPosition.getX() - 1, bombPosition.getY())).thenReturn(mockTileUp);
        when(mockBoard.getTile(bombPosition.getX() + 1, bombPosition.getY())).thenReturn(mockTileDown);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() - 1)).thenReturn(mockTileLeft);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() + 1)).thenReturn(mockTileRight);

        // Call the popOff method
        bombTile.getBehaviorContext().popOff(mockBoard, toRemove);

        // Debug: Print contents of toRemove
        System.out.println("toRemove size: " + toRemove.size());
        for (Tile tile : toRemove) {
            System.out.println(tile);
        }

        // Verify all adjacent tiles were added to the removal set
        assertEquals(4, toRemove.size());
        assertTrue(toRemove.contains(mockTileUp));
        assertTrue(toRemove.contains(mockTileDown));
        assertTrue(toRemove.contains(mockTileLeft));
        assertTrue(toRemove.contains(mockTileRight));
    }

    @Test
    void testBombTilePointsCalculation() {
        // Setup mock board behavior and tile rarities
        Position bombPosition = bombTile.getGridCoordinates();
        when(mockBoard.getTile(bombPosition.getX() - 1, bombPosition.getY())).thenReturn(mockTileUp);
        when(mockBoard.getTile(bombPosition.getX() + 1, bombPosition.getY())).thenReturn(mockTileDown);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() - 1)).thenReturn(mockTileLeft);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() + 1)).thenReturn(mockTileRight);

        when(mockTileUp.getColorRarity()).thenReturn(2);
        when(mockTileDown.getColorRarity()).thenReturn(3);
        when(mockTileLeft.getColorRarity()).thenReturn(1);
        when(mockTileRight.getColorRarity()).thenReturn(4);

        // Debug: Check rarities
        System.out.println("Tile Up Rarity: " + mockTileUp.getColorRarity());
        System.out.println("Tile Down Rarity: " + mockTileDown.getColorRarity());
        System.out.println("Tile Left Rarity: " + mockTileLeft.getColorRarity());
        System.out.println("Tile Right Rarity: " + mockTileRight.getColorRarity());

        // Calculate points
        int points = bombTile.getBehaviorContext().calculatePoints(mockBoard);

        // Debug: Print points
        System.out.println("Calculated points: " + points);

        // Verify points calculation
        assertEquals(10, points); // 2 + 3 + 1 + 4 = 10
    }

}
