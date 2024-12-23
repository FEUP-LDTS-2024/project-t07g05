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

        gui = mock(GUI.class);
        board = mock(Board.class);
        boardViewer = mock(BoardViewer.class);
        scoreViewer = mock(ScoreViewer.class);
        timerViewer = mock(TimerViewer.class);

        gameViewer = new GameViewer(board, boardViewer, scoreViewer, timerViewer);
    }

    @Test
    public void testDrawElementsCallsDrawGameBackground() {
        gameViewer.drawElements(gui);

        verify(gui).drawGameBackground(120, 40);
    }

    @Test
    public void testDrawElementsCallsDrawBoard() {
        gameViewer.drawElements(gui);

        verify(gui).drawBoard(board);
    }

    @Test
    public void testDrawElementsCallsBoardViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(boardViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsScoreViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(scoreViewer).drawElements(gui);
    }

    @Test
    public void testDrawElementsCallsTimerViewerDrawElements() {
        gameViewer.drawElements(gui);

        verify(timerViewer).drawElements(gui);
    }
}
