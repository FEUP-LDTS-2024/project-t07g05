package controller;

import com.ldts.crystalclash.controller.MenuController;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.states.GameState;
import com.ldts.crystalclash.states.ScoresMenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import java.io.IOException;

public class MenuControllerTest {
    private MenuController controller;
    private Menu menu;
    private Game game;

    @BeforeEach
    void setUp() {
        menu = mock(Menu.class);
        game = mock(Game.class);
        controller = new MenuController(menu);
    }

    @Test
    void testStepUpAction() throws Exception {

        controller.step(game, GUI.ACTION.UP, 0);
        verify(menu, times(1)).selectPrevious();
    }

    @Test
    void testStepDownAction() throws Exception {

        controller.step(game, GUI.ACTION.DOWN, 0);
        verify(menu, times(1)).selectNext();
    }

    @Test
    void testStepSelectActionPlay() throws Exception {
        when(menu.isSelectedPlay()).thenReturn(true);

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(game, times(1)).setState(any(GameState.class));
    }

    @Test
    void testStepSelectActionScores() throws IOException {
        when(menu.isSelectedScores()).thenReturn(true);

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(game, times(1)).setState(any(ScoresMenuState.class));
    }

    @Test
    void testStepSelectActionExit() throws Exception {
        when(menu.isSelectedExit()).thenReturn(true);

        controller.step(game, GUI.ACTION.SELECT, 0);

        verify(game).setState(null);
    }
}