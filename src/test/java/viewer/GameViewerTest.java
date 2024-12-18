package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.viewer.TimerViewer;
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

        Timer mockTimer = mock(Timer.class);
        when(mockTimer.getTimeLeft()).thenReturn(89L);

        TimerViewer mockTimerViewer = mock(TimerViewer.class);
        when(mockBoard.getTimer()).thenReturn(mockTimer);

        gameViewer.drawElements(mockGUI);

        verify(mockGUI, times(1)).drawTextInGame(
                any(Position.class),
                eq("TIME LEFT:"),
                eq("#FFFFFF")
        );
        verify(mockGUI, times(1)).drawTextInGame(
                any(Position.class),
                eq("89"),
                eq("#FFFFFF")
        );
    }


}