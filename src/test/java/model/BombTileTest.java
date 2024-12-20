package model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.BombTile;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.model.EmptyTile;
import com.ldts.crystalclash.model.TileMatcher;
import com.ldts.crystalclash.model.Color;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class BombTileTest {

    @Mock
    private Board mockBoard;

    @InjectMocks
    private TileMatcher tileMatcher;

    private BombTile bombTile;

    @BeforeEach
    void setUp() {
        bombTile = new BombTile(new Position(2, 2), new Position(2, 2), Color.RUBY);
    }

    @Test
    void testBombTileAffectsAdjacentTiles() {

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

        List<Tile> matches = new ArrayList<>();
        matches.add(bombTile);
        tileMatcher.matches = matches;

        tileMatcher.popMatches();

        verify(mockBoard).setTile(up.getX(), up.getY(), any(EmptyTile.class));
        verify(mockBoard).setTile(down.getX(), down.getY(), any(EmptyTile.class));
        verify(mockBoard).setTile(left.getX(), left.getY(), any(EmptyTile.class));
        verify(mockBoard).setTile(right.getX(), right.getY(), any(EmptyTile.class));
        verify(mockBoard).setTile(bombTile.getGridCoordinates().getX(), bombTile.getGridCoordinates().getY(), any(EmptyTile.class));
    }

    @Test
    void testBombTileDoesNotAffectOutOfBoundsTiles() {

        when(mockBoard.isValidPosition(anyInt(), anyInt())).thenReturn(false);

        Position invalidUp = new Position(-1, 2);
        Position invalidDown = new Position(4, 2);
        Position invalidLeft = new Position(2, -1);
        Position invalidRight = new Position(2, 4);

        Tile invalidUpTile = mock(Tile.class);
        Tile invalidDownTile = mock(Tile.class);
        Tile invalidLeftTile = mock(Tile.class);
        Tile invalidRightTile = mock(Tile.class);

        List<Tile> matches = new ArrayList<>();
        matches.add(bombTile);
        tileMatcher.matches = matches;

        tileMatcher.popMatches();

        verify(mockBoard, never()).setTile(invalidUp.getX(), invalidUp.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(invalidDown.getX(), invalidDown.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(invalidLeft.getX(), invalidLeft.getY(), any(EmptyTile.class));
        verify(mockBoard, never()).setTile(invalidRight.getX(), invalidRight.getY(), any(EmptyTile.class));
    }

    @Test
    void testBombTileHasCorrectColor() {

        assertEquals("#ff1c20", bombTile.getColor());

    }

    @AfterEach
    void tearDown() {

    }
}
