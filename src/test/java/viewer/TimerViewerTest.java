package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.viewer.TimerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TimerViewerTest {
    private TimerViewer timerViewer;
    private Timer mockTimer;
    private GUI mockGUI;

    @BeforeEach
    void setUp() {
        mockGUI = mock(GUI.class);
        mockTimer = mock(Timer.class);
        timerViewer = new TimerViewer(mockTimer);
    }

    @Test
    void testDrawElementsCallsDrawTextInGameWithCorrectArguments() {

        when(mockTimer.getTimeLeft()).thenReturn(90L);

        timerViewer.drawElements(mockGUI);

        verify(mockGUI).drawTextInGame(any(), eq("TIME LEFT:"), eq("#FFFFFF"));

        verify(mockGUI).drawTextInGame(any(), eq("90"), eq("#FFFFFF"));
    }
}