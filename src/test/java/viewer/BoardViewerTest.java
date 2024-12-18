package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testDrawElementsCallsTileViewer() {

        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        Tile[][] grid = new Tile[][]{{mockTile1, mockTile2}};
        when(mockBoard.getGrid()).thenReturn(grid);

        TileViewer mockTileViewer1 = mock(TileViewer.class);
        TileViewer mockTileViewer2 = mock(TileViewer.class);

        //whenNew(TileViewer.class).withArguments(mockTile1).thenReturn(mockTileViewer1);
        //whenNew(TileViewer.class).withArguments(mockTile2).thenReturn(mockTileViewer2);

        boardViewer.drawElements(mockGUI);

        verify(mockTileViewer1, times(1)).drawElements(mockGUI);
        verify(mockTileViewer2, times(1)).drawElements(mockGUI);
    }
}

