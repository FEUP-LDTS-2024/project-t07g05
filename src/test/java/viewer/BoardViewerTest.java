package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BoardViewerTest {
    private Board board;
    private BoardViewer boardViewer;
    private GUI gui;
    private TileViewer tileViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);
        board = mock(Board.class);

        Tile[][] mockGrid = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mockGrid[i][j] = mock(Tile.class);
            }
        }
        when(board.getGrid()).thenReturn(mockGrid);

        boardViewer = new BoardViewer(board);
    }

    @Test
    public void testDrawElementsCallsDrawTileForEachTile() {

        Tile[][] mockGrid = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mockGrid[i][j] = mock(Tile.class);
            }
        }
        when(board.getGrid()).thenReturn(mockGrid);

        ArgumentCaptor<Tile> tileCaptor = ArgumentCaptor.forClass(Tile.class);

        boardViewer.drawElements(gui);

        verify(gui, times(9)).drawTile(tileCaptor.capture());

        List<Tile> capturedTiles = tileCaptor.getAllValues();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(mockGrid[i][j], capturedTiles.get(i * 3 + j));
            }
        }
    }

    @Test
    public void testDrawElementsInteractionsWithGUI() {
        Tile[][] mockGrid = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mockGrid[i][j] = mock(Tile.class);
            }
        }
        when(board.getGrid()).thenReturn(mockGrid);

        boardViewer.drawElements(gui);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                verify(gui, times(1)).drawTile(mockGrid[i][j]);
            }
        }
    }
}