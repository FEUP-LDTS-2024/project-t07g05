package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BoardViewerTest {
    private Board board;
    private BoardViewer boardViewer;
    private GUI gui;
    private TileViewer tileViewer;

    @BeforeEach
    void setUp() {
        gui = mock(GUI.class);  // Mock the GUI interface
        board = mock(Board.class);  // Mock the Board model

        // Create a mock 3x3 grid
        Tile[][] mockGrid = new Tile[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mockGrid[i][j] = mock(Tile.class);  // Mock each tile
            }
        }
        when(board.getGrid()).thenReturn(mockGrid);  // Return mock grid for the Board

        // Instantiate the BoardViewer with the mocked Board
        boardViewer = new BoardViewer(board);
    }

    @Test
    public void testDrawElementsCallsDrawTileForEachTile() {
        // Capture the arguments passed to drawTile
        ArgumentCaptor<Tile> tileCaptor = ArgumentCaptor.forClass(Tile.class);

        // Call drawElements on the BoardViewer
        boardViewer.drawElements(gui);

        // Verify that drawTile was called 9 times (once for each tile in the 3x3 grid)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Verify that drawTile was called once for each tile in the grid
                verify(gui, times(1)).drawTile(tileCaptor.capture());

                // Ensure that the captured tile is the same as the one from the grid
                assertEquals(board.getGrid()[i][j], tileCaptor.getValue());
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