package model;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {

        board = new Board(5,5,100,100,10,10);
    }

    @Test
    void testGetGrid(){

        assertNotNull(board.getGrid(), "Grid should not be null");
        assertEquals(5, board.getGrid().length, "Grid should have 5 rows");
        assertEquals(5, board.getGrid()[0].length, "Grid should have 5 columns");

    }

    @Test
    void testGetTile(){
        Tile tile = board.getTile(0,0);
        assertNotNull(tile, "Tile at position (0,0) should not be null");
        assertEquals(new Position(0,0), tile.getGridCoordinates(), "Tile coordinates should match (0,0)"    );
    }

    @Test
    void testSetTile(){
        Tile newTile = mock(Tile.class);
        board.setTile(0,0,newTile);
        assertEquals(newTile,board.getTile(0,0), "Tile at position (0,0) should be set correctly");

        verify(newTile).setGridCoordinates(new Position(0,0));
    }

    @Test
    void testSetTileAtInvalidPosition() {
        Tile mockTile = mock(Tile.class);
        board.setTile(-1,-1,mockTile);
        assertNull(board.getTile(-1,-1), "Tile at position (-1,-1) should null");
    }


    @Test
    void testTileMovementOutOfBounds() {
        Tile tile = board.getTile(0, 0);
        assertNull(board.getTileToTheLeft(tile), "Tile to the left of (0,0) should be null");
        assertNull(board.getTileOnBottom(tile), "Tile on bottom of (0,0) should be null");
    }


    @Test
    void testGetTileAtInvalidPosition() {
        Tile tile = board.getTile(10, 10);
        assertNull(tile, "Tile at position (10,10) should be null for invalid positions");
    }


    @Test
    void testGetTileToTheRight() {
        Tile tile = board.getTile(0, 0);
        Tile tileRight = board.getTileToTheRight(tile);
        assertNotNull(tileRight, "Tile to the right of (0,0) should not be null");
        assertEquals(new Position(0, 1), tileRight.getGridCoordinates(), "Tile to the right should have coordinates (0,1)");
    }


    @Test
    void testGetTileToTheLeft() {
        Tile tile = board.getTile(0, 1);
        Tile tileLeft = board.getTileToTheLeft(tile);
        assertNotNull(tileLeft, "Tile to the left of (0,1) should not be null");
        assertEquals(new Position(0, 0), tileLeft.getGridCoordinates(), "Tile to the left should have coordinates (0,0)");
    }


    @Test
    void testGetTileOnTop() {
        Tile tile = board.getTile(1, 0);
        Tile tileOnTop = board.getTileOnTop(tile);
        assertNotNull(tileOnTop, "Tile on top of (1,0) should not be null");
        assertEquals(new Position(2, 0), tileOnTop.getGridCoordinates(), "Tile on top should have coordinates (2,0)");
    }


    @Test
    void testGetTileOnBottom() {
        Tile tile = board.getTile(1, 0);
        Tile tileOnBottom = board.getTileOnBottom(tile);
        assertNotNull(tileOnBottom, "Tile on bottom of (1,0) should not be null");
        assertEquals(new Position(0, 0), tileOnBottom.getGridCoordinates(), "Tile on bottom should have coordinates (0,0)");
    }

    @Test
    void testInitializeBoard() {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getColumns(); col++) {
                Tile tile = board.getTile(row, col);
                assertNotNull(tile, "Tile at position (" + row + "," + col + ") should not be null");
                assertEquals(new Position(row, col), tile.getGridCoordinates(), "Tile coordinates should match (" + row + "," + col + ")");
            }
        }
    }

    @Test
    void testCalculateScreenPosition() {
        Position gridPosition = new Position(1, 1);
        Position screenPosition = board.calculateScreenPosition(gridPosition);
        assertNotNull(screenPosition, "Screen position should not be null");
        assertEquals(new Position(board.getStartX() + board.getColumnSpacing(),
                board.getStartY() + board.getRowSpacing()), screenPosition, "Screen position should be calculated correctly");
    }
}
