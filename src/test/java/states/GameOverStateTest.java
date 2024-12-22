package states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.GameOverController;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.viewer.GameOverViewer;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;

import com.ldts.crystalclash.viewer.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameOverStateTest {

    private GameOverState gameOverState;
    private GameOver mockGameOver;
    private Game mockGame;
    private GUI mockGUI;

    @BeforeEach
    public void setUp() {
        mockGameOver = mock(GameOver.class);
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        gameOverState = new GameOverState(mockGameOver);
    }

    @AfterEach
    public void tearDown() {
        reset(mockGame, mockGUI, mockGameOver);
    }

    @Test
    public void testGetModel() {
        assertEquals(mockGameOver, gameOverState.getModel());
    }

    @Test
    public void testStepCallsControllerAndViewerWithReflection() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.UP);

        GameOverController originalController = new GameOverController(mockGameOver);
        GameOverController controllerSpy = spy(originalController);
        GameOverViewer originalViewer = new GameOverViewer(mockGameOver);
        GameOverViewer viewerSpy = spy(originalViewer);

        GameOverState testState = new GameOverState(mockGameOver) {
            @Override
            protected Controller<GameOver> getController() {
                return controllerSpy;
            }

            @Override
            protected Viewer<GameOver> getViewer() {
                return viewerSpy;
            }
        };

        testState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(controllerSpy, times(1)).step(mockGame, GUI.ACTION.UP, time);
        verify(viewerSpy, times(1)).draw(mockGUI);
    }

    @Test
    public void testViewerInitialization() {
        GameOverViewer viewer = new GameOverViewer(mockGameOver);
        assertNotNull(viewer);
    }

    @Test
    public void testControllerInitialization() {
        GameOverController controller = new GameOverController(mockGameOver);
        assertNotNull(controller);
    }

    @Test
    public void testStepHandlesNullAction() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.NONE);

        GameOverController controllerSpy = spy(new GameOverController(mockGameOver));
        GameOverViewer viewerSpy = spy(new GameOverViewer(mockGameOver));

        GameOverState testState = new GameOverState(mockGameOver) {
            @Override
            protected Controller<GameOver> getController() {
                return controllerSpy;
            }

            @Override
            protected Viewer<GameOver> getViewer() {
                return viewerSpy;
            }
        };

        assertDoesNotThrow(() -> testState.step(mockGame, mockGUI, time));
        verify(controllerSpy, times(1)).step(mockGame, GUI.ACTION.NONE, time);  // Assert that ACTION.NONE was passed
        verify(viewerSpy, times(1)).draw(mockGUI);
    }

}