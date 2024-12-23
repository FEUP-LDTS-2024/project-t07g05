package model;

import com.ldts.crystalclash.factories.TileFactory;
import com.ldts.crystalclash.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.lang.reflect.Field;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() throws Exception {
        TileFactory tileFactoryMock = mock(TileFactory.class);

        // Setup the mock to return a DIAMOND tile for the first tile and random tiles for others
        when(tileFactoryMock.createRandomTile(any(Position.class), any(Position.class)))
                .thenAnswer(invocation -> {
                    Position gridPosition = invocation.getArgument(1, Position.class);
                    // Force the first tile to be DIAMOND
                    if (gridPosition.getX() == 0 && gridPosition.getY() == 0) {
                        return new GemTile(
                                invocation.getArgument(0, Position.class),
                                gridPosition,
                                Color.DIAMOND);
                    } else {
                        return new GemTile(
                                invocation.getArgument(0, Position.class),
                                gridPosition,
                                Color.SAPPHIRE); // Return a random color, for example, SAPPHIRE
                    }
                });

        board = new Board(5, 5, 500, 500, 10, 10);

        Field tileFactoryField = Board.class.getDeclaredField("tileFactory");
        tileFactoryField.setAccessible(true);
        tileFactoryField.set(board, tileFactoryMock);
    }


    @Test
    void testGetTile_ValidPosition() {
        Tile tile = board.getTile(0, 0);
        assertNotNull(tile);
    }

    @Test
    void testGetTile_InvalidPosition() {
        Tile tile = board.getTile(-1, -1);
        assertNull(tile);

        tile = board.getTile(10, 10);
        assertNull(tile);
    }

    @Test
    void testSetTile() {
        Tile mockTile = mock(Tile.class);
        board.setTile(2, 2, mockTile);

        assertEquals(mockTile, board.getTile(2, 2));
    }

    @Test
    void testCalculateScreenPosition() {
        Position position = board.calculateScreenPosition(new Position(0, 0));
        assertEquals(new Position(board.getStartX(), board.getStartY()), position);

        position = board.calculateScreenPosition(new Position(1, 1));
        assertEquals(new Position(board.getStartX() + board.getColumnSpacing(), board.getStartY() + board.getRowSpacing()), position);
    }

    @Test
    void testGetTileToTheRight() {
        Tile tile = board.getTile(0, 0);
        Tile rightTile = board.getTileToTheRight(tile);
        assertNotNull(rightTile);
    }

    @Test
    void testInvalidTileNeighbors() {
        Tile tile = board.getTile(0, 4);
        assertNull(board.getTileToTheRight(tile));

        tile = board.getTile(4, 0);
        assertNull(board.getTileOnBottom(tile));
    }

    @Test
    void testScoreManipulation() {
        Score mockScore = mock(Score.class);
        board.setScore(mockScore);

        when(mockScore.getScore()).thenReturn(100);
        assertEquals(100, board.getScore().getScore());
    }

    @Test
    void testTilePosition() {
        Tile tile = board.getTile(0, 0);
        assertNotNull(tile);
        assertEquals(new Position(0, 0), tile.getGridCoordinates());
    }

}

