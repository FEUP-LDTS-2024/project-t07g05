package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.GameOver;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.states.ScoresMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class GameOverControllerTest {

    private GameOverController gameOverController;
    private Game game;
    private GUI.ACTION action;
    private ArgumentCaptor<GameState> gameStateCaptor;
    private ArgumentCaptor<ScoresMenuState> scoresMenuStateCaptor;

    @BeforeEach
    public void setUp() throws IOException {
        game = mock(Game.class);
        action = GUI.ACTION.SELECT;

        GameOver gameOverMock = mock(GameOver.class);
        when(gameOverMock.isSelectedPlayAgain()).thenReturn(false);
        when(gameOverMock.isSelectedScores()).thenReturn(false);
        when(gameOverMock.isSelectedExit()).thenReturn(false);

        gameOverController = new GameOverController(gameOverMock);

        gameStateCaptor = ArgumentCaptor.forClass(GameState.class);
        scoresMenuStateCaptor = ArgumentCaptor.forClass(ScoresMenuState.class);
    }

    @Test
    public void testStep_PlayAgain() throws IOException {
        GameOver gameOverMock = gameOverController.getModel();
        when(gameOverMock.isSelectedPlayAgain()).thenReturn(true);

        gameOverController.step(game, action, 0);

        verify(game).setState(gameStateCaptor.capture());
        GameState capturedState = gameStateCaptor.getValue();
        assert capturedState != null;
    }

    @Test
    public void testStep_Scores() throws IOException {
        GameOver gameOverMock = gameOverController.getModel();
        when(gameOverMock.isSelectedScores()).thenReturn(true);

        gameOverController.step(game, action, 0);

        verify(game).setState(scoresMenuStateCaptor.capture());
        ScoresMenuState capturedState = scoresMenuStateCaptor.getValue();
        assert capturedState != null;
    }

    @Test
    public void testStep_Exit() throws IOException {
        GameOver gameOverMock = gameOverController.getModel();
        when(gameOverMock.isSelectedExit()).thenReturn(true);

        gameOverController.step(game, action, 0);

        verify(game).setState(null);
    }

    @AfterEach
    public void tearDown() {
        gameOverController = null;
        game = null;
        action = null;
        gameStateCaptor = null;
        scoresMenuStateCaptor = null;
    }

}
