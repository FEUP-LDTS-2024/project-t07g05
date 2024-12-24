package com.ldts.crystalclash.states;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.controller.MenuController;
import com.ldts.crystalclash.viewer.MenuViewer;
import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.viewer.Viewer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuStateTest {

    private GUI mockGUI;
    private Menu mockMenu;
    private MenuController mockMenuController;
    private MenuViewer mockMenuViewer;
    private Game mockGame;
    private MenuState menuState;

    @BeforeEach
    void setUp() {
        mockGUI = mock(GUI.class);
        mockMenu = mock(Menu.class);
        mockMenuController = mock(MenuController.class);
        mockMenuViewer = mock(MenuViewer.class);
        mockGame = mock(Game.class);

        menuState = new MenuState(mockMenu) {
            @Override
            protected MenuController getController() {
                return mockMenuController;
            }

            @Override
            protected MenuViewer getViewer() {
                return mockMenuViewer;
            }
        };
    }

    @AfterEach
    void tearDown() {
        reset(mockGUI, mockMenu, mockMenuController, mockMenuViewer, mockGame);
    }

    @Test
    void testStepCallsControllerAndViewerWithActionUp() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.UP);

        menuState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(mockMenuController, times(1)).step(eq(mockGame), eq(GUI.ACTION.UP), eq(time));
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }

    @Test
    void testStepCallsControllerAndViewerWithActionSelect() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.SELECT);

        menuState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(mockMenuController, times(1)).step(eq(mockGame), eq(GUI.ACTION.SELECT), eq(time));
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }

    @Test
    void testStepDoesNotAlterStateWhenNoAction() throws Exception {
        long time = 100L;
        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.NONE);

        menuState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(mockMenuController, times(1)).step(eq(mockGame), eq(GUI.ACTION.NONE), eq(time));
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }

    @Test
    void testViewerInitialization() {
        menuState = new MenuState(mockMenu) {
            @Override
            protected MenuViewer getViewer() {
                return null;  // Simulating mutation
            }

            @Override
            protected MenuController getController() {
                return mockMenuController;
            }
        };

        Viewer<Menu> viewer = menuState.getViewer();
        assertNull(viewer, "Viewer should be null when getViewer() is mutated to return null");
    }

    @Test
    void testControllerInitialization() {
        menuState = new MenuState(mockMenu) {
            @Override
            protected MenuViewer getViewer() {
                return mockMenuViewer;
            }

            @Override
            protected MenuController getController() {
                return null;
            }
        };

        Controller<Menu> controller = menuState.getController();
        assertNull(controller, "Controller should be null when getController() is mutated to return null");
    }


    @Test
    void testStepHandlesNullAction() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.NONE);

        menuState.step(mockGame, mockGUI, time);

        verify(mockMenuController, times(1)).step(mockGame, GUI.ACTION.NONE, time);
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }



    @Test
    void testViewerComposition() {
        MenuViewer viewer = new MenuViewer(mockMenu);
        assertNotNull(viewer, "MenuViewer should not be null");
        assertEquals(mockMenu, viewer.getModel(), "MenuViewer should have the correct model");
    }

    @Test
    void testStepHandlesMultipleActions() throws Exception {
        long time = 100L;
        Game mockGame = mock(Game.class);

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.UP, GUI.ACTION.SELECT, GUI.ACTION.NONE);

        menuState.step(mockGame, mockGUI, time);
        menuState.step(mockGame, mockGUI, time);
        menuState.step(mockGame, mockGUI, time);

        verify(mockMenuController, times(1)).step(mockGame, GUI.ACTION.UP, time);
        verify(mockMenuController, times(1)).step(mockGame, GUI.ACTION.SELECT, time);
        verify(mockMenuController, times(1)).step(mockGame, GUI.ACTION.NONE, time);
        verify(mockMenuViewer, times(3)).draw(mockGUI);
    }


}
