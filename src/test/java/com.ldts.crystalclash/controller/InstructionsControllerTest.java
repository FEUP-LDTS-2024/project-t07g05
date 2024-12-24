package com.ldts.crystalclash.controller;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;

class InstructionsControllerTest {

    private InstructionsController controller;
    private Game game;

    @BeforeEach
    void setUp() {
        Instructions instructions = new Instructions();
        controller = new InstructionsController(instructions);
        game = mock(Game.class);
    }

    @Test
    void quitActionChangesToMenuState() throws IOException {
        GUI.ACTION action = GUI.ACTION.QUIT;

        controller.step(game, action, 0);

        ArgumentCaptor<MenuState> captor = ArgumentCaptor.forClass(MenuState.class);
        verify(game, times(1)).setState(captor.capture());

        assertInstanceOf(MenuState.class, captor.getValue());
    }


    @Test
    void noActionDoesNotChangeState() throws IOException {
        GUI.ACTION action = GUI.ACTION.NONE;

        controller.step(game, action, 0);

        verify(game, never()).setState(any());
    }
}

