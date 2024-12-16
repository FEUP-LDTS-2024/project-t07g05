package viewer;

import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.gui.LanternaGUI;

import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BoardViewerTest {
    private Board board;
    private BoardViewer boardViewer;
    private LanternaGUI gui;
    private Tile[][] grid;

    @BeforeEach
    void setUp() {
       grid = new Tile[5][5];
       for(int i = 0; i < 5; i++) {
           for(int j = 0; j < 5; j++) {
               grid[i][j] = mock(Tile.class);
           }
       }

       board = mock(Board.class);
       when(board.getGrid()).thenReturn(grid);

       gui = mock(LanternaGUI.class);
       boardViewer = new BoardViewer(board);
    }

    @Test
    void testDraw() {
        boardViewer.draw(gui);

        verify(gui).drawBoard(board);

        for(Tile[] row : grid) {
            for(Tile tile : row) {
                TileViewer tileViewer = mock(TileViewer.class);
                tileViewer.draw(gui);
                verify(tileViewer).draw(gui);
            }
        }

    }
}


