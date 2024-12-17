package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class BoardViewerTest {
    private Board mockBoard;
    private BoardViewer boardViewer;
    private GUI mockGUI;

    @BeforeEach
    void setUp() {
        mockGUI = mock(GUI.class);
        mockBoard = mock(Board.class);

        boardViewer = new BoardViewer(mockBoard);
    }

    @Test
    void testDrawElementsCallsTileViewer() throws IOException {

        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        Tile[][] grid = new Tile[][]{
                {mockTile1, mockTile2}
        };

        when(mockBoard.getGrid()).thenReturn(grid);
        boardViewer.drawElements(mockGUI);
        verify(mockGUI, times(1)).clear();
        verify(mockGUI, times(1)).refresh();

        for (Tile[] row : grid) {
            for (Tile tile : row) {
                TileViewer tileViewer = new TileViewer(tile);
                tileViewer.drawElements(mockGUI);
            }
        }


    }

}
