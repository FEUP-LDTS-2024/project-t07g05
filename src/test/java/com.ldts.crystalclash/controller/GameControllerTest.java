package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.model.*;
import com.ldts.crystalclash.states.GameOverState;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
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

        Tile mockTile = mock(Tile.class);
        when(mockTile.getGridCoordinates()).thenReturn(new Position(0, 0));

        when(mockBoard.getCurrentTile()).thenReturn(mockTile);

        gameController = new GameController(mockBoard);
    }


    @Test
    void testStepSetsGameToMenuStateOnQuitAction() throws IOException {
        gameController.step(mockGame, GUI.ACTION.QUIT, System.currentTimeMillis());

        verify(mockGame, times(1)).setState(argThat(state -> state instanceof MenuState));
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
        BoardController boardController = new BoardController(mockBoard);
        BoardController spyBoardController = spy(boardController);

        gameController = new GameController(mockBoard) {
            @Override
            public void step(Game game, GUI.ACTION action, long time) throws IOException {
                spyBoardController.step(game, action, time);
            }
        };

        gameController.step(mockGame, GUI.ACTION.UP, System.currentTimeMillis());

        verify(spyBoardController, times(1)).step(eq(mockGame), eq(GUI.ACTION.UP), anyLong());
    }


    @Test
    void testScoreIsIncrementedCorrectly() throws IOException {
        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        BoardController controller = new BoardController(mockBoard);

        controller.step(mockGame, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(mockScore, times(1)).addScore(anyInt());
    }


    @Test
    void testScoreIncrement() {
        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        mockScore.addScore(10);

        verify(mockScore, times(1)).addScore(10);
    }


    @Test
    void testStepAction() throws IOException {
        Board mockBoard = mock(Board.class);
        Tile mockTile = mock(Tile.class);
        Score mockScore = mock(Score.class);

        when(mockBoard.getCurrentTile()).thenReturn(mockTile);
        when(mockBoard.getScore()).thenReturn(mockScore);

        Position mockPosition = new Position(0, 0);
        when(mockTile.getGridCoordinates()).thenReturn(mockPosition);

        BoardController controller = new BoardController(mockBoard);

        controller.step(mock(Game.class), GUI.ACTION.UP, System.currentTimeMillis());

        verify(mockBoard, times(3)).getCurrentTile();
        verify(mockTile, times(2)).getGridCoordinates();
        verify(mockScore).addScore(anyInt());
    }


    @Test
    void testShiftTilesDownAndRefillBoard() throws IOException {
        BoardController controller = new BoardController(mockBoard);
        BoardController spyBoardController = spy(controller);

        doNothing().when(spyBoardController).shiftTilesDown();
        doNothing().when(spyBoardController).refillBoard();

        spyBoardController.step(mockGame, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(spyBoardController, times(1)).shiftTilesDown();
        verify(spyBoardController, times(1)).refillBoard();
    }

    @Test
    void testScoreIncrementsAfterValidAction() throws IOException {

        Score mockScore = mock(Score.class);
        when(mockBoard.getScore()).thenReturn(mockScore);

        gameController.step(mockGame, GUI.ACTION.DOWN, System.currentTimeMillis());

        verify(mockScore, times(1)).addScore(anyInt());
    }

    @AfterEach
    void tearDown() {
        mockBoard = null;
        mockGame = null;
        gameController = null;
    }

}
