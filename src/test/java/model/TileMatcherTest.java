package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TileMatcherTest {
    private Board board;
    private TileMatcher tileMatcher;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        tileMatcher = new TileMatcher(board);
    }

    @Test
    void testFindMatches() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        tileMatcher.findMatches();
        assertNotNull(tileMatcher.matches, "Matches should not be null");
    }

    @Test
    void testPopMatches() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);


        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        when(tile1.getGridCoordinates()).thenReturn(new Position(0, 0));
        when(tile2.getGridCoordinates()).thenReturn(new Position(1, 1));

        tileMatcher.matches = new ArrayList<>();
        tileMatcher.matches.add(tile1);
        tileMatcher.matches.add(tile2);


        int initialSize = tileMatcher.matches.size();
        assertTrue(initialSize > 0, "Initial size of matches should be greater than 0");


        tileMatcher.popMatches();
        assertTrue(tileMatcher.matches.isEmpty(), "Matches should be empty after popMatches()");

        verify(board).setTile(eq(0), eq(0), any(EmptyTile.class));
        verify(board).setTile(eq(1), eq(1), any(EmptyTile.class));

    }
}