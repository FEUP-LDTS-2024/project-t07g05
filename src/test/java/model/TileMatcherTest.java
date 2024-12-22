package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileMatcherTest {

    private Board board;
    private TileMatcher tileMatcher;

    @BeforeEach
    void setUp() {
        board = new Board(5, 5, 500, 500, 50, 50);
        tileMatcher = new TileMatcher(board);
    }

    @Test
    void testFindHorizontalMatches() {
        Tile tile1 = new GemTile(new Position(0, 0), new Position(0, 0), Color.DIAMOND);
        Tile tile2 = new GemTile(new Position(1, 0), new Position(0, 1), Color.DIAMOND);
        Tile tile3 = new GemTile(new Position(2, 0), new Position(0, 2), Color.DIAMOND);

        board.setTile(0, 0, tile1);
        board.setTile(0, 1, tile2);
        board.setTile(0, 2, tile3);

        tileMatcher.findMatches();
        List<Tile> matches = tileMatcher.matches;

        assertEquals(3, matches.size());
        assertTrue(matches.contains(tile1));
        assertTrue(matches.contains(tile2));
        assertTrue(matches.contains(tile3));
    }

    @Test
    void testFindVerticalMatches() {
        // Set up a vertical match of 3
        Tile tile1 = new GemTile(new Position(0, 0), new Position(0, 0), Color.SAPPHIRE);
        Tile tile2 = new GemTile(new Position(1, 0), new Position(1, 0), Color.SAPPHIRE);
        Tile tile3 = new GemTile(new Position(2, 0), new Position(2, 0), Color.SAPPHIRE);

        board.setTile(0, 0, tile1);
        board.setTile(1, 0, tile2);
        board.setTile(2, 0, tile3);

        tileMatcher.findMatches();
        List<Tile> matches = tileMatcher.matches;

        assertEquals(3, matches.size());
        assertTrue(matches.contains(tile1));
        assertTrue(matches.contains(tile2));
        assertTrue(matches.contains(tile3));
    }

    @Test
    void testNoMatches() {
        Color[] colors = Color.values();
        int colorIndex = 0;

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                Color color = colors[colorIndex];
                colorIndex = (colorIndex + 1) % colors.length;
                Tile tile = new GemTile(new Position(row, col), new Position(row, col), color);
                board.setTile(row, col, tile);
            }
        }

        tileMatcher.findMatches();
        assertTrue(tileMatcher.matches.isEmpty(), "Matches list should be empty, but it's not.");
    }


    @Test
    void testPopMatches() {
        Tile tile1 = new GemTile(new Position(0, 0), new Position(0, 0), Color.RUBY);
        Tile tile2 = new GemTile(new Position(0, 1), new Position(0, 1), Color.RUBY);
        Tile tile3 = new GemTile(new Position(0, 2), new Position(0, 2), Color.RUBY);

        board.setTile(0, 0, tile1);
        board.setTile(0, 1, tile2);
        board.setTile(0, 2, tile3);

        tileMatcher.findMatches();
        tileMatcher.popMatches();

        assertInstanceOf(EmptyTile.class, board.getTile(0, 0));
        assertInstanceOf(EmptyTile.class, board.getTile(0, 1));
        assertInstanceOf(EmptyTile.class, board.getTile(0, 2));
    }

    @Test
    void testCalculateScore() {
        // Set up matches and verify score calculation
        Tile tile1 = new GemTile(new Position(0, 0), new Position(0, 0), Color.EMERALD);
        Tile tile2 = new GemTile(new Position(0, 1), new Position(0, 1), Color.EMERALD);
        Tile tile3 = new GemTile(new Position(0, 2), new Position(0, 2), Color.EMERALD);

        board.setTile(0, 0, tile1);
        board.setTile(0, 1, tile2);
        board.setTile(0, 2, tile3);

        tileMatcher.findMatches();
        int score = tileMatcher.calculateScore();

        assertEquals(15, score); // Adjust the expected value based on your scoring logic
    }
}
