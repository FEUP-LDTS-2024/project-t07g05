package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class BoardControllerTest {
    private BoardController boardController;
    private Board board;
    private TileFactory tileFactoryMock;

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
        Tile tile1 = new GemTile(new Position(100, 100), new Position(0, 0), Color.RUBY);
        Tile tile2 = new GemTile(new Position(150, 100), new Position(0, 1), Color.SAPPHIRE);

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
        Tile tile1 = new GemTile(new Position(100, 100), new Position(0, 0), Color.RUBY);
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

    /// test step



}