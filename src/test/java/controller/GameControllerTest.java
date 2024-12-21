package controller;

import com.ldts.crystalclash.controller.BoardController;
import com.ldts.crystalclash.controller.GameController;
import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameControllerTest {

    private GameController gameController;
    private Game mockGame;
    private Board mockBoard;

    @BeforeEach

    void setUp() {
        mockBoard = mock(Board.class);
        mockGame = mock(Game.class);
        gameController = new GameController(mockBoard);
        Timer mockTimer = mock(Timer.class);
        when(mockTimer.getTimeLeft()).thenReturn(30L);
        when(mockBoard.getTimer()).thenReturn(mockTimer);

        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        gameController = new GameController(mockBoard);
    }


    @Test
    void testStepSetsGameToMenuStateOnQuitAction() throws IOException {
        gameController.step(mockGame, GUI.ACTION.QUIT, System.currentTimeMillis());

        verify(mockGame, times(1)).setState(any(MenuState.class));
        verifyNoInteractions(mockBoard);
    }

    @Test
    void testStepDoesNotCallBoardControllerOnQuitAction() throws IOException {
        BoardController mockBoardController = mock(BoardController.class);

        gameController.step(mockGame, GUI.ACTION.QUIT, System.currentTimeMillis());

        verifyNoInteractions(mockBoardController);
    }

    @Test
    void testStepSetsGameToGameOverStateWhenTimeRunsOut() throws IOException {
        when(mockBoard.getTimer().getTimeLeft()).thenReturn(0L);

        gameController.step(mockGame, GUI.ACTION.UP, System.currentTimeMillis());

        verify(mockGame, times(1)).setState(any(GameOverState.class));
    }


    @Test
    void testStepDelegatesActionToBoardController() throws IOException {
        BoardController spyBoardController = spy(new BoardController(mockBoard));
        doNothing().when(spyBoardController).step(any(Game.class), any(GUI.ACTION.class), anyLong());

        gameController.step(mockGame, GUI.ACTION.UP, System.currentTimeMillis());

        verify(spyBoardController, times(1)).step(mockGame, GUI.ACTION.UP, System.currentTimeMillis());
    }

    @Test
    void testScoreIncrementsAfterValidAction() throws IOException {
        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        gameController.step(mockGame, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(mockScore, times(1)).addScore(anyInt());
    }


    @Test
    void testNoActionOnInvalidGUIAction() throws IOException {
        gameController.step(mockGame, null, System.currentTimeMillis());

        verifyNoInteractions(mockBoard);
        verifyNoInteractions(mockGame);
    }

    @Test
    void testScoreIncrement() {
        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        mockScore.addScore(10);

        verify(mockScore, times(1)).addScore(10);
    }

}
