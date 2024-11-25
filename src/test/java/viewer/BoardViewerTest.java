package viewer;

import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.gui.LanternaGUI;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BoardViewerTest {
    private Board mockBoard;
    private BoardViewer boardViewer;
    private LanternaGUI mockGUI;

    @BeforeEach
    void setUp() {
        mockBoard = mock(Board.class);
        mockGUI = mock(LanternaGUI.class);
        boardViewer = new BoardViewer(mockBoard);
    }

    @Test
    void testDraw() {
        int width = 10;
        int height = 5;

        when(mockBoard.getWidth()).thenReturn(width);
        when(mockBoard.getHeight()).thenReturn(height);

        Tile[][] grid = new Tile[2][2];
        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        grid[0][0] = mockTile1;
        grid[0][1] = mockTile2;
        when(mockBoard.getGrid()).thenReturn(grid);

        boardViewer.draw(mockGUI);

        verify(mockGUI).setBackgroundColor(TextColor.Factory.fromString("#2e3440"));
        verify(mockGUI).fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        verify(mockTile1, atLeastOnce());
        verify(mockTile2, atLeastOnce());

        for (Tile[] row : grid) {
            for (Tile cell : row) {
                verify(cell, atLeastOnce());
            }
        }
    }
}


