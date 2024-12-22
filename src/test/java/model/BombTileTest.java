package model;

import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.BombTileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BombTileTest {

    private BombTile bombTile;
    private Board mockBoard;
    private Tile mockAdjacentTile;
    private Set<Tile> toRemove;

    @BeforeEach
    void setUp() {
        // Create a mock board and bombTile instance
        mockBoard = mock(Board.class);
        Position screenPosition = new Position(100, 100);
        Position gridCoordinates = new Position(2, 2);
        Color mockColor = mock(Color.class);  // Assuming Color is a valid class with proper constructor
        bombTile = new BombTile(screenPosition, gridCoordinates, mockColor);

        // Mock an adjacent tile to be affected by the bomb
        mockAdjacentTile = mock(Tile.class);

        // Initialize the toRemove set as a HashSet
        toRemove = new HashSet<>();
    }

    @Test
    void testBombTileBehaviorRemovesAdjacentTiles() {
        // Simulate the adjacent tile being next to the bombTile
        Position bombPosition = bombTile.getGridCoordinates();

        // Mock valid neighboring positions around the bombTile (e.g., top, bottom, left, right)
        Position topPosition = new Position(bombPosition.getX() - 1, bombPosition.getY());
        Position bottomPosition = new Position(bombPosition.getX() + 1, bombPosition.getY());
        Position leftPosition = new Position(bombPosition.getX(), bombPosition.getY() - 1);
        Position rightPosition = new Position(bombPosition.getX(), bombPosition.getY() + 1);

        when(mockBoard.isValidPosition(topPosition.getX(), topPosition.getY())).thenReturn(true);
        when(mockBoard.isValidPosition(bottomPosition.getX(), bottomPosition.getY())).thenReturn(true);
        when(mockBoard.isValidPosition(leftPosition.getX(), leftPosition.getY())).thenReturn(true);
        when(mockBoard.isValidPosition(rightPosition.getX(), rightPosition.getY())).thenReturn(true);

        // Simulate getting the adjacent tiles
        when(mockBoard.getTile(topPosition.getX(), topPosition.getY())).thenReturn(mockAdjacentTile);
        when(mockBoard.getTile(bottomPosition.getX(), bottomPosition.getY())).thenReturn(mockAdjacentTile);
        when(mockBoard.getTile(leftPosition.getX(), leftPosition.getY())).thenReturn(mockAdjacentTile);
        when(mockBoard.getTile(rightPosition.getX(), rightPosition.getY())).thenReturn(mockAdjacentTile);

        // Call the popOff method to simulate the bomb explosion
        BehaviorContext behaviorContext = bombTile.getBehaviorContext();
        BombTileBehavior bombTileBehavior = (BombTileBehavior) behaviorContext.getBehavior();
        bombTileBehavior.popOff(bombTile, mockBoard, toRemove);

        // Verify that the adjacent tiles are added to the removal set
        verify(toRemove, times(4)).add(mockAdjacentTile);  // Should be called for each of the 4 directions (top, bottom, left, right)
    }

    @Test
    void testBombTilePointsCalculation() {
        // Simulate neighboring tiles with some colors (and color rarities)
        Position bombPosition = bombTile.getGridCoordinates();

        // Mock some adjacent tiles with varying rarities
        Tile mockTileUp = mock(Tile.class);
        Tile mockTileDown = mock(Tile.class);
        Tile mockTileLeft = mock(Tile.class);
        Tile mockTileRight = mock(Tile.class);

        when(mockBoard.getTile(bombPosition.getX() - 1, bombPosition.getY())).thenReturn(mockTileUp);
        when(mockBoard.getTile(bombPosition.getX() + 1, bombPosition.getY())).thenReturn(mockTileDown);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() - 1)).thenReturn(mockTileLeft);
        when(mockBoard.getTile(bombPosition.getX(), bombPosition.getY() + 1)).thenReturn(mockTileRight);

        when(mockTileUp.getColorRarity()).thenReturn(2);
        when(mockTileDown.getColorRarity()).thenReturn(3);
        when(mockTileLeft.getColorRarity()).thenReturn(1);
        when(mockTileRight.getColorRarity()).thenReturn(4);

        // Call the calculatePoints method to calculate the bombTile's points
        int points = bombTile.getBehaviorContext().calculatePoints(mockBoard);

        // The points should be the sum of the adjacent tiles' rarities
        assertEquals(10, points);  // 2 + 3 + 1 + 4 = 10
    }
}
