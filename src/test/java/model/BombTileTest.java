package model;

import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.strategy.BombTileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BombTileTest {
    private BombTile bombTile;
    private Board mockBoard;
    private Tile mockTile1;
    private Tile mockTile2;
    private Tile mockTile3;
    private Tile mockTile4;
    private Position screenPosition;
    private Position gridCoordinates;
    private Color color;

    @BeforeEach
    public void setUp() {
        screenPosition = new Position(10, 10);
        gridCoordinates = new Position(5, 5);
        color = Color.RUBY;
        bombTile = new BombTile(screenPosition, gridCoordinates, color);
        mockBoard = mock(Board.class);
        mockTile1 = mock(Tile.class);
        mockTile2 = mock(Tile.class);
        mockTile3 = mock(Tile.class);
        mockTile4 = mock(Tile.class);
    }

    @Test
    public void testBombTileInitialization() {
        assertEquals("@", bombTile.getSymbol());
        assertEquals(screenPosition, bombTile.getScreenPosition());
        assertEquals(gridCoordinates, bombTile.getGridCoordinates());
        assertEquals(color.getHexCode(), bombTile.getColor());
        assertNotNull(bombTile.getBehaviorContext());
        assertInstanceOf(BombTileBehavior.class, bombTile.getBehaviorContext().getBehavior());
    }

    @Test
    public void testPopOffBehavior() {
        Set<Tile> toRemove = new HashSet<>();
        when(mockBoard.isValidPosition(4, 5)).thenReturn(true);
        when(mockBoard.isValidPosition(6, 5)).thenReturn(true);
        when(mockBoard.isValidPosition(5, 4)).thenReturn(true);
        when(mockBoard.isValidPosition(5, 6)).thenReturn(true);

        when(mockBoard.getTile(4, 5)).thenReturn(mockTile1);
        when(mockBoard.getTile(6, 5)).thenReturn(mockTile2);
        when(mockBoard.getTile(5, 4)).thenReturn(mockTile3);
        when(mockBoard.getTile(5, 6)).thenReturn(mockTile4);


        bombTile.getBehaviorContext().popOff(mockBoard, toRemove);

        assertTrue(toRemove.contains(mockTile1));
        assertTrue(toRemove.contains(mockTile2));
        assertTrue(toRemove.contains(mockTile3));
        assertTrue(toRemove.contains(mockTile4));
    }

    @Test
    public void testCalculatePoints() {
        // Arrange
        when(mockBoard.isValidPosition(4, 5)).thenReturn(true);
        when(mockBoard.isValidPosition(6, 5)).thenReturn(true);
        when(mockBoard.isValidPosition(5, 4)).thenReturn(true);
        when(mockBoard.isValidPosition(5, 6)).thenReturn(true);

        when(mockBoard.getTile(4, 5)).thenReturn(mockTile1);
        when(mockBoard.getTile(6, 5)).thenReturn(mockTile2);
        when(mockBoard.getTile(5, 4)).thenReturn(mockTile3);
        when(mockBoard.getTile(5, 6)).thenReturn(mockTile4);

        when(mockTile1.getColorRarity()).thenReturn(1);
        when(mockTile2.getColorRarity()).thenReturn(2);
        when(mockTile3.getColorRarity()).thenReturn(3);
        when(mockTile4.getColorRarity()).thenReturn(4);

        int points = bombTile.getBehaviorContext().calculatePoints(mockBoard);

        assertEquals(10, points);
    }

    @Test
    public void testNoAdjacentTiles() {
        Set<Tile> toRemove = new HashSet<>();
        when(mockBoard.isValidPosition(4, 5)).thenReturn(false);
        when(mockBoard.isValidPosition(6, 5)).thenReturn(false);
        when(mockBoard.isValidPosition(5, 4)).thenReturn(false);
        when(mockBoard.isValidPosition(5, 6)).thenReturn(false);

        bombTile.getBehaviorContext().popOff(mockBoard, toRemove);

        assertTrue(toRemove.isEmpty());
    }
}
