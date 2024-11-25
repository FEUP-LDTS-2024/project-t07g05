package model;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.TileMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        tileMatcher.popMatches();
        int initialSize = tileMatcher.matches.size();
        tileMatcher.popMatches();
        assertTrue(tileMatcher.matches.isEmpty(), "Matches should be empty");
        assertTrue(initialSize > 0, "Initial size of Matches should be greater than 0");
    }
}