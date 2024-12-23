package model;

import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TileMatcherTest {

    private Board mockedBoard;
    private TileMatcher tileMatcher;

    @BeforeEach
    void setUp() {
        mockedBoard = mock(Board.class);
        tileMatcher = new TileMatcher(mockedBoard);
    }

    @Test
    void testFindHorizontalMatchesWithMock() {
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        Tile tile3 = mock(Tile.class);

        when(tile1.getColor()).thenReturn(Color.DIAMOND.getHexCode());
        when(tile2.getColor()).thenReturn(Color.DIAMOND.getHexCode());
        when(tile3.getColor()).thenReturn(Color.DIAMOND.getHexCode());

        when(mockedBoard.getRows()).thenReturn(5);
        when(mockedBoard.getColumns()).thenReturn(5);
        when(mockedBoard.getTile(0, 0)).thenReturn(tile1);
        when(mockedBoard.getTile(0, 1)).thenReturn(tile2);
        when(mockedBoard.getTile(0, 2)).thenReturn(tile3);

        tileMatcher.findMatches();
        List<Tile> matches = tileMatcher.matches;

        assertEquals(3, matches.size());
        assertTrue(matches.contains(tile1));
        assertTrue(matches.contains(tile2));
        assertTrue(matches.contains(tile3));
    }

    @Test
    void testNoMatchesWithMock() {
        when(mockedBoard.getRows()).thenReturn(5);
        when(mockedBoard.getColumns()).thenReturn(5);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Tile tile = mock(Tile.class);
                when(tile.getColor()).thenReturn("Color" + row + col);
                when(mockedBoard.getTile(row, col)).thenReturn(tile);
            }
        }

        tileMatcher.findMatches();

        assertTrue(tileMatcher.matches.isEmpty());
    }
}
