package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.viewer.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameViewerTest {
    private GameViewer gameViewer;
    private Board mockBoard;
    private GUI mockGUI;

    @BeforeEach
    void setUp() {
        mockGUI = mock(GUI.class);
        mockBoard = mock(Board.class);
        gameViewer = new GameViewer(mockBoard);
    }

    @Test
    void testDrawElementsCallsTimerViewer() {

        Timer realTimer = new Timer();
        realTimer.start();

        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);
        when(mockScore.getScore()).thenReturn(100);

        Tile mockTile1 = mock(Tile.class);
        Tile mockTile2 = mock(Tile.class);
        Tile[][] grid = new Tile[][]{
                {mockTile1, mockTile2}
        };
        when(mockBoard.getGrid()).thenReturn(grid);

        when(mockBoard.getTimer()).thenReturn(realTimer);

        gameViewer.drawElements(mockGUI);

        verify(mockGUI, times(1)).drawText(any(Position.class), anyString(), eq("white"));
    }

}
