package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.Score;
import com.ldts.crystalclash.viewer.ScoreViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ScoreViewerTest {
    private ScoreViewer scoreViewer;
    private GUI gui;

    @BeforeEach
    public void setUp() {

        Score score = mock(Score.class);
        when(score.getScore()).thenReturn(123);

        scoreViewer = new ScoreViewer(score);

        gui = mock(GUI.class);
    }

    @Test
    public void testDrawElementsCallsDrawTextInGame() {

        scoreViewer.drawElements(gui);

        verify(gui).drawTextInGame(new Position(100, 5), "SCORE:", "#FFFFFF");
        verify(gui).drawTextInGame(new Position(100, 7), "123", "#FFFFFF");
    }
}
