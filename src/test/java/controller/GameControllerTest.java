package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.model.Score;
import com.ldts.crystalclash.model.Board;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameControllerTest {
    private GameController controller;
    private Game mockGame;
    private BoardController mockBoardController;
    private Timer mockTimer;
    private Board mockBoard;

    @BeforeEach

    void setUp() {
        mockBoard = mock(Board.class);
        mockGame = mock(Game.class);
        mockBoardController = mock(BoardController.class);
        mockTimer = mock(Timer.class);

        when(mockBoard.getTimer()).thenReturn(mockTimer);

        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        controller = new GameController(mockBoard);
    }


    @Test
    void testStepCallsBoardControllerOnValidAction() throws IOException {
        GUI.ACTION action = GUI.ACTION.UP;
        long time = 100L;

        controller.step(mockGame, action, time);

        verify(mockBoardController, times(1)).step(mockGame, action, time);
    }

    @Test
    void testStepSetsGameToGameOverStateWhenTimeExpires() throws IOException {
        when(mockTimer.getTimeLeft()).thenReturn(0L);

        controller.step(mockGame, GUI.ACTION.UP, 100L);

        verify(mockGame).setState(any(GameOverState.class));
    }

    @Test
    void testStepSetsGameToMenuStateOnQuitAction() throws IOException {
        GUI.ACTION quitAction = GUI.ACTION.QUIT;

        controller.step(mockGame, quitAction, 100L);

        verify(mockGame).setState(any(MenuState.class));
    }

    @Test
    void testStepDoesNotCallBoardControllerOnQuitAction() throws IOException {
        GUI.ACTION quitAction = GUI.ACTION.QUIT;

        controller.step(mockGame, quitAction, 100L);

        verify(mockBoardController, times(0)).step(any(Game.class), eq(quitAction), anyLong());
    }

    @Test
    void testScoreIncrement() {

        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        mockScore.addScore(10);

        verify(mockScore, times(1)).addScore(10);
    }

}
