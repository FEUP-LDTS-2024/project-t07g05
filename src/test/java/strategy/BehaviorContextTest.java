package strategy;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.strategy.BehaviorContext;
import com.ldts.crystalclash.strategy.TileBehavior;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BehaviorContextTest {

    private Tile mockTile;
    private TileBehavior mockBehavior;
    private Board mockBoard;
    private BehaviorContext context;

    @BeforeEach
    void setUp() {
        mockTile = mock(Tile.class);
        mockBehavior = mock(TileBehavior.class);
        mockBoard = mock(Board.class);

        context = new BehaviorContext(mockTile, mockBehavior);
    }

    @Test
    void testGetAndSetBehavior() {
        TileBehavior newBehavior = mock(TileBehavior.class);

        context.setBehavior(newBehavior);

        assertEquals(newBehavior, context.getBehavior(), "Behavior should be updated correctly.");
    }

    @Test
    void testPopOffDelegation() {
        Set<Tile> toRemove = new HashSet<>();

        context.popOff(mockBoard, toRemove);

        verify(mockBehavior, times(1)).popOff(mockTile, mockBoard, toRemove);
    }

    @Test
    void testCalculatePointsDelegation() {
        when(mockBehavior.calculatePoints(mockTile, mockBoard)).thenReturn(10);

        int points = context.calculatePoints(mockBoard);

        verify(mockBehavior, times(1)).calculatePoints(mockTile, mockBoard);
        assertEquals(10, points, "Points should be delegated to the behavior.");
    }

    @Test
    void testPopOffWithNullBehavior() {
        context.setBehavior(null);
        Set<Tile> toRemove = new HashSet<>();

        context.popOff(mockBoard, toRemove);

        assertTrue(toRemove.isEmpty(), "No tiles should be removed when behavior is null.");
    }

    @Test
    void testCalculatePointsWithNullBehavior() {

        context.setBehavior(null);

        int points = context.calculatePoints(mockBoard);

        assertEquals(0, points, "Points should be 0 when behavior is null.");
    }
}
