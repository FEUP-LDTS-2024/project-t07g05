package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.viewer.GameOverViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class GameOverViewerTest {

    private GameOver gameOver;
    private GUI gui;
    private GameOverViewer gameOverViewer;

    @BeforeEach
    void setUp() {
        gameOver = Mockito.mock(GameOver.class);
        gui = Mockito.mock(GUI.class);
        gameOverViewer = new GameOverViewer(gameOver);
    }

    @Test
    void drawElements_drawsBackground() {
        gameOverViewer.drawElements(gui);

        verify(gui, times(1)).drawGameBackground(120, 40);
    }

    @Test
    void drawElements_drawsOptions() {
        when(gameOver.getNumberOptions()).thenReturn(3);
        when(gameOver.getSelectedOption(0)).thenReturn("PLAY AGAIN");
        when(gameOver.getSelectedOption(1)).thenReturn("SCORES");
        when(gameOver.getSelectedOption(2)).thenReturn("EXIT");
        when(gameOver.isSelected(0)).thenReturn(true);
        when(gameOver.isSelected(1)).thenReturn(false);
        when(gameOver.isSelected(2)).thenReturn(false);

        gameOverViewer.drawElements(gui);

        verify(gui).drawText(new Position(52, 20), "PLAY AGAIN", "#00FFFF");
        verify(gui).drawText(new Position(52, 21), "SCORES", "#FFFFFF");
        verify(gui).drawText(new Position(52, 22), "EXIT", "#FFFFFF");
    }

    @Test
    void drawElements_drawsGameOverTitle() {
        gameOverViewer.drawElements(gui);

        verify(gui).drawText(new Position(50, 5), "G A M E  O V E R:", "#eaff00");
    }

    @Test
    void drawElements_drawsScore() {
        when(gameOver.getScore()).thenReturn(12345);

        gameOverViewer.drawElements(gui);

        verify(gui).drawText(new Position(50, 10), "YOUR SCORE:12345", "#FFFFFF");
    }

    @Test
    void drawElements_handlesExceptions() {
        doThrow(new RuntimeException("GUI error"))
                .when(gui).drawGameBackground(120, 40);

        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> gameOverViewer.drawElements(gui));
    }
}
