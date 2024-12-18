package states;

import com.ldts.crystalclash.controller.GameOverController;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.viewer.GameOverViewer;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameOverStateTest {
    private GameOverState gameOverState;

    private GameOver mockGameOver;
    private GameOverController mockController;
    private GameOverViewer mockViewer;
    private Game mockGame;
    private GUI mockGUI;

    @BeforeEach
    public void setUp() {

        mockGameOver = mock(GameOver.class);
        mockController = mock(GameOverController.class);
        mockViewer = mock(GameOverViewer.class);
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);

        gameOverState = spy(new GameOverState(mockGameOver));


        doReturn(mockController).when(gameOverState).getController();
        doReturn(mockViewer).when(gameOverState).getViewer();
    }

    @Test
    public void testGetModel() {
        assertEquals(mockGameOver, gameOverState.getModel());
    }
}


