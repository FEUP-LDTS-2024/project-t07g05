package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.viewer.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameOverViewerTest {

    private GUI gui;
    private GameOverViewer gameOverViewer;

    @BeforeEach
    public void setup() {
        gui = mock(GUI.class);
        GameOver gameOver = mock(GameOver.class);
        gameOverViewer = new GameOverViewer(gameOver);
    }

    @Test
    public void testDrawElementsCallsClear() {
        gameOverViewer.drawElements(gui);
        verify(gui).clear();
    }

    @Test
    public void testDrawElementsCallsDrawGameBackground() {
        gameOverViewer.drawElements(gui);
        verify(gui).drawGameBackground(120, 40);
    }

    @Test
    public void testDrawElementsCallsDrawText() {
        gameOverViewer.drawElements(gui);

        ArgumentCaptor<Position> positionCaptor = ArgumentCaptor.forClass(Position.class);
        ArgumentCaptor<String> textCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> colorCaptor = ArgumentCaptor.forClass(String.class);

        verify(gui, times(2)).drawText(positionCaptor.capture(), textCaptor.capture(), colorCaptor.capture());

        assertEquals(new Position(37, 5), positionCaptor.getAllValues().getFirst());
        assertEquals("G A M E  O V E R:", textCaptor.getAllValues().getFirst());
        assertEquals("#eaff00", colorCaptor.getAllValues().getFirst());

        assertEquals(new Position(40, 10), positionCaptor.getAllValues().get(1));
        assertEquals("YOUR SCORE:0", textCaptor.getAllValues().get(1));
        assertEquals("#FFFFFF", colorCaptor.getAllValues().get(1));
    }


    @Test
    public void testDrawElementsCallsDrawLogo() {
        gameOverViewer.drawElements(gui);

        verify(gui).drawLogo(17, 5, "#f7fbfc");
    }

    @Test
    public void testDrawElementsCallsRefresh() {
        gameOverViewer.drawElements(gui);
        try {
            verify(gui).refresh();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
