package viewer;

import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.viewer.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GameViewerTest {

    private LanternaGUI gui;
    private GameViewer gameViewer;

    @BeforeEach
    void setUp() {
        gui = mock(LanternaGUI.class);
        Board board = mock(Board.class);
        gameViewer = new GameViewer(board);
    }

    @Test
    void testDraw() throws java.io.IOException{
        gameViewer.draw(gui);

        verify(gui).drawGameBackground(120,40);

        verify(gui).refresh();
    }
}
