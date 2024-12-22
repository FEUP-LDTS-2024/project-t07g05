package states;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class GameStateTest {

    private GameState gameState;
    private Board mockBoard;
    private GUI mockGUI;
    private GameController mockController;
    private GameViewer mockViewer;

    @BeforeEach
    void setUp() {

        mockBoard = mock(Board.class);
        mockGUI = mock(GUI.class);

        mockController = mock(GameController.class);
        mockViewer = mock(GameViewer.class);

        gameState = new GameState(mockBoard) {
            @Override
            protected GameViewer getViewer() {
                return mockViewer;
            }

            @Override
            protected GameController getController() {
                return mockController;
            }
        };
    }

    @Test
    void testStepCallsControllerAndViewer() throws IOException {
        long time = 100L;
        Game mockGame = mock(Game.class);

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.UP);

        gameState.step(mockGame, mockGUI, time);

        verify(mockController, times(1)).step(mockGame, GUI.ACTION.UP, time);
        verify(mockViewer, times(1)).draw(mockGUI);
        verify(mockGUI, times(1)).getNextAction();
    }

}
