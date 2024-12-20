package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class BoardControllerTest {
    private BoardController boardController;
    private Board board;
    private TileFactory tileFactoryMock;
    private Tile setUpTile(Position position, Color color) {
        return new GemTile(new Position(100, 100), position, color);
    }


    @BeforeEach
    void setUp() {

        tileFactoryMock = mock(TileFactory.class);
        TileMatcher tileMatcherMock = mock(TileMatcher.class);

        board = new Board(5, 5, 800, 600, 50, 50);
        boardController = new BoardController(board);

        boardController.tileFactory = tileFactoryMock;
        boardController.tileMatcher = tileMatcherMock;
    }

    @Test
    void testSwapTilesValid() {
        Tile tile1 = setUpTile(new Position(0, 0), Color.RUBY);
        Tile tile2 = setUpTile(new Position(0, 1), Color.SAPPHIRE);


        board.setTile(0, 0, tile1);
        board.setTile(0, 1, tile2);

        boardController.swapTiles(tile1, tile2);

        assertEquals(tile2, board.getTile(0, 0));
        assertEquals(tile1, board.getTile(0, 1));
        assertEquals(new Position(0, 0), tile2.getGridCoordinates());
        assertEquals(new Position(0, 1), tile1.getGridCoordinates());
    }

    @Test
    void testSwapTilesNull() {
        Tile tile1 = setUpTile(new Position(0, 0), Color.RUBY);
        board.setTile(0, 0, tile1);
        boardController.swapTiles(tile1, null);
        assertEquals(tile1, board.getTile(0, 0));
    }


    @Test
    void testMoveCurrentTileValid() {
        Tile initialTile = board.getTile(0, 0);
        Tile targetTile = board.getTile(0, 1);

        boardController.moveCurrentTile(0,1);

        assertFalse(initialTile.isCursorOn());
        assertTrue(targetTile.isCursorOn());
        assertEquals(targetTile, board.getCurrentTile());

    }

    @Test
    void testMoveCurrentTileInvalid() {
        Tile initialTile = board.getTile(0, 0);
        boardController.moveCurrentTile(-1,-1);

        assertTrue(initialTile.isCursorOn());
        assertEquals(initialTile, board.getCurrentTile());
    }


    @Test
    void testShiftTilesDown() {

        Tile emptyTile = new EmptyTile(new Position(100, 100), new Position(4, 0));
        board.setTile(4, 0, emptyTile);

        Tile newTile = new GemTile(new Position(100, 100), new Position(4, 0), Color.SAPPHIRE);
        when(tileFactoryMock.createRandomTile(any(), any())).thenReturn(newTile);

        boardController.refillBoard();
        assertEquals(newTile, board.getTile(4, 0));

    }

    @Test
    void testRefillBoard() {
        Tile emptyTile = new EmptyTile(new Position(100, 100), new Position(4, 0));
        board.setTile(4, 0, emptyTile);

        Tile newTile = new GemTile(new Position(100, 100), new Position(4, 0), Color.SAPPHIRE);
        when(tileFactoryMock.createRandomTile(any(), any())).thenReturn(newTile);

        boardController.refillBoard();

        assertEquals(newTile, board.getTile(4, 0));
    }

    @Test
    void testSwapTilesOutOfBounds() {
        Tile tile1 = new GemTile(new Position(100, 100), new Position(0, 0), Color.RUBY);
        Tile tile2 = new GemTile(new Position(150, 100), new Position(0, 1), Color.SAPPHIRE);

        // Set tiles
        board.setTile(0, 0, tile1);
        board.setTile(0, 1, tile2);

        // Call swap tiles method
        boardController.swapTiles(tile1, tile2);

        // Assert the correct swap
        assertEquals(tile2, board.getTile(0, 0)); // tile2 should be at (0, 0)
        assertEquals(tile1, board.getTile(0, 1)); // tile1 should be at (0, 1)
    }


    @Test
    void testMoveCurrentTileOutOfBounds() {
        Tile initialTile = board.getTile(0, 0);

        boardController.moveCurrentTile(-1, -1);

        assertTrue(initialTile.isCursorOn());
        assertEquals(initialTile, board.getCurrentTile());
    }

    @Test
    void testComplexTileInteraction() {

        Tile initialTile1 = setUpTile(new Position(0, 0), Color.RUBY);
        Tile initialTile2 = setUpTile(new Position(0, 1), Color.SAPPHIRE);
        Tile emptyTile = new EmptyTile(new Position(100, 100), new Position(4, 0));

        board.setTile(0, 0, initialTile1);
        board.setTile(0, 1, initialTile2);
        board.setTile(4, 0, emptyTile);


        Tile newTile = new GemTile(new Position(100, 100), new Position(4, 0), Color.EMERALD);
        when(tileFactoryMock.createRandomTile(any(), any())).thenReturn(newTile);


        boardController.swapTiles(initialTile1, initialTile2);
        boardController.moveCurrentTile(4, 0);
        boardController.refillBoard();

        assertEquals(initialTile2, board.getTile(0, 0));
        assertEquals(initialTile1, board.getTile(0, 1));
        assertEquals(newTile, board.getTile(4, 0));
        assertTrue(board.getCurrentTile().isCursorOn());
        assertEquals(new Position(4, 0), board.getCurrentTile().getGridCoordinates());
    }


    @Test
    void testRefillBoardWithInvalidTile() {
        Tile emptyTile = new EmptyTile(new Position(100, 100), new Position(4, 0));
        board.setTile(4, 0, emptyTile);

        Tile invalidTile = new EmptyTile(new Position(100, 100), new Position(4, 0));
        when(tileFactoryMock.createRandomTile(any(), any())).thenReturn(invalidTile);  // Invalid tile

        boardController.refillBoard();

        assertEquals(invalidTile, board.getTile(4, 0));
    }

    @Test
    void testMoveCurrentTileAtTopEdge() {
        Tile initialTile = board.getTile(0, 0);

        boardController.moveCurrentTile(-1, 0);

        assertTrue(initialTile.isCursorOn());
        assertEquals(initialTile, board.getCurrentTile());
    }

    @Test
    void testMoveCurrentTileAtBottomEdge() {
        Tile initialTile = board.getTile(4, 0);

        boardController.moveCurrentTile(5, 0); // Invalid move (out of bounds)

        // Assert that the initial tile is still the current tile and the cursor remains on it
        assertTrue(initialTile.isCursorOn());
        assertEquals(initialTile, board.getCurrentTile());
    }

    @Test
    void testMoveCurrentTileAtRightEdge() {
        Tile initialTile = board.getTile(0, 4); // Assuming rightmost column is column 4

        // Try to move right (out of bounds)
        boardController.moveCurrentTile(0, 5); // Beyond the rightmost column

        // Assert that the tile stays in place (cursor remains on the initial tile)
        assertTrue(initialTile.isCursorOn());
        assertEquals(initialTile, board.getCurrentTile());
    }


    @Test
    void testRefillBoardVerifiesTileFactoryCall() {
        Tile emptyTile = new EmptyTile(new Position(100, 100), new Position(4, 0));
        board.setTile(4, 0, emptyTile);

        Tile newTile = new GemTile(new Position(100, 100), new Position(4, 0), Color.SAPPHIRE);
        when(tileFactoryMock.createRandomTile(any(), any())).thenReturn(newTile);

        boardController.refillBoard();

        verify(tileFactoryMock, times(1)).createRandomTile(any(), any());

        assertEquals(newTile, board.getTile(4, 0));
    }

}