package viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.viewer.BoardViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

public class GameViewerTest {

    private LanternaGUI gui;
    private GameViewer gameViewer;
    private BoardViewer boardViewer;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        gui = mock(LanternaGUI.class);
        Board board = mock(Board.class);
        boardViewer = mock(BoardViewer.class);
        gameViewer = new GameViewer(board);

        when(board.getRows()).thenReturn(8);
        when(board.getColumns()).thenReturn(8);

        Field boardViewerField = GameViewer.class.getDeclaredField("boardViewer");
        boardViewerField.setAccessible(true);
        boardViewerField.set(gameViewer, boardViewer);
    }

    @Test
    void testDraw() throws java.io.IOException{
        gameViewer.draw(gui);

        verify(gui).drawGameBackground(120,40);
        verify(boardViewer).draw(gui);
        verify(gui).refresh();
    }
}
