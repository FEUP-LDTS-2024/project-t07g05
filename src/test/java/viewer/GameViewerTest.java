package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.BoardViewer;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.viewer.ScoreViewer;
import com.ldts.crystalclash.viewer.TimerViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameViewerTest {

    private GUI gui;
    private Board board;
    private GameViewer gameViewer;
    private BoardViewer boardViewer;
    private ScoreViewer scoreViewer;
    private TimerViewer timerViewer;

    @BeforeEach
    public void setup() {
        // Mock dependencies
        gui = mock(GUI.class);
        board = mock(Board.class);

        // Create the GameViewer instance
        gameViewer = new GameViewer(board);

        // Manually instantiate BoardViewer, ScoreViewer, TimerViewer as mocks
        boardViewer = mock(BoardViewer.class);
        scoreViewer = mock(ScoreViewer.class);
        timerViewer = mock(TimerViewer.class);
    }

    @Test
    public void testDrawElementsCallsDrawGameBackground() {
        // Call the drawElements method of GameViewer
        gameViewer.drawElements(gui);

        // Verify that the drawGameBackground method was called once
        verify(gui).drawGameBackground(120, 40);
    }

    @Test
    public void testDrawElementsCallsDrawBoard() {
        // Call the drawElements method of GameViewer
        gameViewer.drawElements(gui);

        // Verify that the drawBoard method was called once with the board argument
        verify(gui).drawBoard(board);
    }

    @Test
    public void testDrawElementsCallsBoardViewerDrawElements() {
        // We manually call the drawElements method of BoardViewer for testing
        gameViewer.drawElements(gui);

        // Verify that BoardViewer's drawElements method was called once
        verify(boardViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsScoreViewerDrawElements() {
        // We manually call the drawElements method of ScoreViewer for testing
        gameViewer.drawElements(gui);

        // Verify that ScoreViewer's drawElements method was called once
        verify(scoreViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsTimerViewerDrawElements() {
        // We manually call the drawElements method of TimerViewer for testing
        gameViewer.drawElements(gui);

        // Verify that TimerViewer's drawElements method was called once
        verify(timerViewer).drawElements(gui);
    }
}
