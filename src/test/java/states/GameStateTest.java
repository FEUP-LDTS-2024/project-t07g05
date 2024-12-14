package states;

import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.viewer.GameViewer;
import com.ldts.crystalclash.controller.BoardController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class GameStateTest {
    private GameState gameState;

    @BeforeEach
    void setUp() {
        Board board = mock(Board.class);
        gameState = new GameState(board);
    }

    @Test
    void testGetViewer() throws Exception {

        Method getViewerMethod = GameState.class.getDeclaredMethod("getViewer");
        getViewerMethod.setAccessible(true);

        Object viewer = getViewerMethod.invoke(gameState);
        assertNotNull(viewer);
        assertInstanceOf(GameViewer.class, viewer);
    }

    @Test
    void testGetController() throws Exception {
        Method getControllerMethod = GameState.class.getDeclaredMethod("getController");
        getControllerMethod.setAccessible(true);

        Object controller = getControllerMethod.invoke(gameState);
        assertNotNull(controller);
        assertInstanceOf(BoardController.class, controller);
    }


}