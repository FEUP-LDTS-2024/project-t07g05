package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import java.io.IOException;


class BoardControllerTest {
    private BoardController controller;
    private Board board;

    @BeforeEach
    void setUp() {
        board = mock(Board.class);
        controller = new BoardController(board);
    }

    @Test
    void testSwapTiles() {
        Tile tile1 = mock(Tile.class);
        Tile tile2 = mock(Tile.class);
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(1, 1);

        when(tile1.getGridCoordinates()).thenReturn(pos1);
        when(tile2.getGridCoordinates()).thenReturn(pos2);

        controller.swapTiles(tile1, tile2);

        verify(board).setTile(0, 0, tile2);
        verify(board).setTile(1, 1, tile1);

        verify(tile1).setGridCoordinates(pos2);
        verify(tile2).setGridCoordinates(pos1);

    }

    @Test
    void testMoveCurrentTile() {
        Tile tile = mock(Tile.class);
        when(board.getCurrentTile()).thenReturn(tile);
        when(board.isValidPosition(1, 1)).thenReturn(true);

        controller.moveCurrentTile(1, 1);

        verify(board).setCurrentTile(tile);
        verify(tile).setCursorOn(true);
    }

    @Test
    void testShiftTilesDown() {

        Tile emptyTile = mock(Tile.class);
        Position pos = new Position(0, 0);

        when(board.getTile(anyInt(), anyInt())).thenReturn(emptyTile);
        when(emptyTile.getGridCoordinates()).thenReturn(pos);

        controller.shiftTilesDown();

        verify(board, atLeastOnce()).getTile(anyInt(), anyInt());
    }

    @Test
    void testRefillBoard() {
        when(board.getRows()).thenReturn(5);
        when(board.getColumns()).thenReturn(5);

        Position position = new Position(0, 0);
        when(board.getTile(0,0)).thenReturn(new EmptyTile(position, position));

        TileFactory tileFactory = mock(TileFactory.class);
        Tile newTile = mock(Tile.class);
        when(tileFactory.createRandomTile(any(Position.class), any(Position.class))).thenReturn(newTile);


        controller.tileFactory = tileFactory;
        controller.refillBoard();

        verify(board).setTile(eq(0), eq(0), eq(newTile));
    }

    @Test
    void testStep() throws IOException {
        Game game = mock(Game.class);
        GUI gui = mock(GUI.class);
        Tile currentTile = mock(Tile.class);

        when(gui.getNextAction()).thenReturn(GUI.ACTION.UP);
        when(board.getCurrentTile()).thenReturn(currentTile);

        Tile tileOnTop = mock(Tile.class);
        when(board.getTileOnTop(currentTile)).thenReturn(tileOnTop);

        BoardController controller = new BoardController(board);
        controller.step(game, GUI.ACTION.SELECT_TILE, 0);

        verify(board).getTileOnTop(currentTile);
    }


}