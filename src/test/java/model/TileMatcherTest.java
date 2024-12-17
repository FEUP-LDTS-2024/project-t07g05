package model;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.EmptyTile;
import com.ldts.crystalclash.model.TileMatcher;
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
    void testMatchesIsNotNull() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        tileMatcher.findMatches();

        assertNotNull(tileMatcher.matches, "Matches should not be null");
    }

    @Test
    void testNoMatchesFound() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Tile tile1 = mock(Tile.class);
        when(tile1.getColor()).thenReturn("Red");

        Tile tile2 = mock(Tile.class);
        when(tile2.getColor()).thenReturn("Blue");

        Tile tile3 = mock(Tile.class);
        when(tile3.getColor()).thenReturn("Green");

        when(board.getTile(0, 0)).thenReturn(tile1);
        when(board.getTile(0, 1)).thenReturn(tile2);
        when(board.getTile(0, 2)).thenReturn(tile3);

        for (int row = 1; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Tile emptyTile = mock(Tile.class);
                when(emptyTile.getColor()).thenReturn("Empty");
                when(board.getTile(row, col)).thenReturn(emptyTile);
            }
        }

        tileMatcher.findMatches();

        assertTrue(tileMatcher.matches.isEmpty(), "Matches should be empty");
    }




    @Test
    void testMatchesAreCleared() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        when(tile1.getGridCoordinates()).thenReturn(new Position(0, 0));
        when(tile2.getGridCoordinates()).thenReturn(new Position(1, 1));

        tileMatcher.matches = new ArrayList<>();
        tileMatcher.matches.add(tile1);
        tileMatcher.matches.add(tile2);

        assertTrue(true, "Matches should not be empty initially");

        tileMatcher.popMatches();

        assertTrue(tileMatcher.matches.isEmpty(), "Matches should be cleared");

        verify(board).setTile(eq(0), eq(0), any(EmptyTile.class));
        verify(board).setTile(eq(1), eq(1), any(EmptyTile.class));
    }

    @Test
    void testHorizontalMatch() {
        // Set up the board with rows and columns
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        // Mock the tiles involved in the match
        Tile tile1 = mock(Tile.class);
        when(tile1.getColor()).thenReturn("Red");

        Tile tile2 = mock(Tile.class);
        when(tile2.getColor()).thenReturn("Red");

        Tile tile3 = mock(Tile.class);
        when(tile3.getColor()).thenReturn("Red");

        // Mock the board's specific tiles to return the expected tiles at the matching positions
        when(board.getTile(0, 0)).thenReturn(tile1);
        when(board.getTile(0, 1)).thenReturn(tile2);
        when(board.getTile(0, 2)).thenReturn(tile3);

        // Mock the other tiles on the board to return some neutral or non-matching color
        for (int row = 1; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Tile emptyTile = mock(Tile.class);
                when(emptyTile.getColor()).thenReturn("Empty");  // Return a non-matching color
                when(board.getTile(row, col)).thenReturn(emptyTile);
            }
        }

        // Now run the match finder
        tileMatcher.findMatches();

        // Assert that matches were found (horizontal match at row 0, columns 0, 1, and 2)
        assertFalse(tileMatcher.matches.isEmpty(), "Matches should be found");
    }



    @Test
    void testVerticalMatch() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        Tile tile3 = mock(Tile.class);

        when(tile1.getColor()).thenReturn("Blue");
        when(tile2.getColor()).thenReturn("Blue");
        when(tile3.getColor()).thenReturn("Blue");

        when(board.getTile(0, 0)).thenReturn(tile1);
        when(board.getTile(1, 0)).thenReturn(tile2);
        when(board.getTile(2, 0)).thenReturn(tile3);

        tileMatcher.findMatches();

        assertFalse(tileMatcher.matches.isEmpty(), "Matches should be found");
    }
}