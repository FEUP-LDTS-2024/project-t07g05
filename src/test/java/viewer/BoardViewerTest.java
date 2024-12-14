package viewer;

import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.gui.LanternaGUI;

import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BoardViewerTest {
    private Board board;
    private BoardViewer boardViewer;
    private LanternaGUI gui;

    @BeforeEach
    void setUp() {
        Tile[][] grid = new Tile[5][5];
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

        try (var mockedTileViewer = mockConstruction(TileViewer.class)) {

            boardViewer.draw(gui);

            verify(gui).drawBoard(board);

            assertEquals(25, mockedTileViewer.constructed().size());

            for (int i = 0; i < mockedTileViewer.constructed().size(); i++) {
                TileViewer tileViewer = mockedTileViewer.constructed().get(i);
                verify(tileViewer).draw(gui);
            }
        }
    }
}


