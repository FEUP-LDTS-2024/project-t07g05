package model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.EmptyTile;
import com.ldts.crystalclash.model.Color;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;

import org.junit.jupiter.api.*;

public class EmptyTileTest {

    private Board mockBoard;
    private EmptyTile emptyTile;

    @BeforeEach
    void setUp() {

        mockBoard = mock(Board.class);
        emptyTile = new EmptyTile(new Position(2, 2), new Position(2, 2), Color.EMERALD);
    }


    @Test
    void testEmptyTileDoesNotAffectAdjacentTiles() {

        Position up = new Position(1, 2);
        Position down = new Position(3, 2);
        Position left = new Position(2, 1);
        Position right = new Position(2, 3);


        Tile upTile = mock(Tile.class);
        Tile downTile = mock(Tile.class);
        Tile leftTile = mock(Tile.class);
        Tile rightTile = mock(Tile.class);

        when(mockBoard.isValidPosition(anyInt(), anyInt())).thenReturn(true);
        when(mockBoard.getTile(up.getX(), up.getY())).thenReturn(upTile);
        when(mockBoard.getTile(down.getX(), down.getY())).thenReturn(downTile);
        when(mockBoard.getTile(left.getX(), left.getY())).thenReturn(leftTile);
        when(mockBoard.getTile(right.getX(), right.getY())).thenReturn(rightTile);

        mockBoard.setTile(emptyTile.getGridCoordinates().getX(), emptyTile.getGridCoordinates().getY(), emptyTile);

        verify(mockBoard, never()).setTile(up.getX(), up.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(down.getX(), down.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(left.getX(), left.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(right.getX(), right.getY(), any(EmptyTile.class));


        verify(mockBoard).setTile(emptyTile.getGridCoordinates().getX(), emptyTile.getGridCoordinates().getY(), emptyTile);
    }

    @Test
    void testEmptyTileSymbol() {

        assertEquals(" ", emptyTile.getSymbol(), "The symbol of the EmptyTile should be a space");
    }

    @AfterEach
    void tearDown() {
        // Cleanup if necessary
    }

    /// To do : Test for color
}

