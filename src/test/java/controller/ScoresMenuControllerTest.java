package controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.ScoresMenuController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.states.MenuState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ScoresMenuControllerTest {

    private ScoresMenuController scoresMenuController;
    private Game game;
    private GUI.ACTION action;
    private ArgumentCaptor<MenuState> menuStateCaptor;

    @BeforeEach
    public void setUp() throws IOException {
        game = mock(Game.class);
        action = GUI.ACTION.QUIT;

        scoresMenuController = new ScoresMenuController(new ScoresMenu());

        menuStateCaptor = ArgumentCaptor.forClass(MenuState.class);
    }

    @Test
    public void testStep_QuitAction() throws IOException {
        scoresMenuController.step(game, action, 0);

        verify(game).setState(menuStateCaptor.capture());
        MenuState capturedState = menuStateCaptor.getValue();

        assert capturedState != null;
    }

    @Test
    public void testStep_NoAction() throws IOException {
        action = GUI.ACTION.SELECT;

        scoresMenuController.step(game, action, 0);

        verify(game, times(0)).setState(any());
    }


    @AfterEach
    void tearDown() {
        scoresMenuController = null;
        game = null;
        action = null;
        menuStateCaptor = null;
    }

}
