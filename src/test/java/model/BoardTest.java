package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ldts.crystalclash.model.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(3, 3, 10, 10, 5, 5);
    }

    @Test
    void testInitialTileCursor() {
        Tile initialTile = board.getCurrentTile();

        assertTrue(initialTile.isCursorOn(), "Cursor should be on the initial tile.");
    }

    @Test
    void testMoveCursorRight() {
        Tile initialTile = board.getCurrentTile();
        Tile nextTile = board.getTileToTheRight(initialTile);

        initialTile.setCursorOn(false);
        nextTile.setCursorOn(true);

        assertFalse(initialTile.isCursorOn(), "Cursor should no longer be on the initial tile.");

        assertTrue(nextTile.isCursorOn(), "Cursor should be on the new tile.");
    }

    @Test
    void testMoveCursorDown() {
        Tile initialTile = board.getCurrentTile();
        Tile nextTile = board.getTileOnBottom(initialTile);

        initialTile.setCursorOn(false);
        nextTile.setCursorOn(true);

        assertFalse(initialTile.isCursorOn(), "Cursor should no longer be on the initial tile.");

        assertTrue(nextTile.isCursorOn(), "Cursor should be on the new tile.");
    }

    @Test
    void testInvalidTileMovement() {
        Tile initialTile = board.getTile(0, 0);

        Tile invalidTile = board.getTile(-1, -1);

        assertNull(invalidTile, "Invalid tile should return null.");
        assertTrue(initialTile.isCursorOn(), "Cursor should remain on the initial tile.");
    }
}
