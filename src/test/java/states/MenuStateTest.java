package states;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Menu;
import com.ldts.crystalclash.controller.MenuController;
import com.ldts.crystalclash.viewer.MenuViewer;
import com.ldts.crystalclash.states.MenuState;
import com.ldts.crystalclash.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void testStepDoesNotCallControllerWhenNoAction() throws Exception {
        long time = 100L;
        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.NONE);
        menuState.step(mockGame, mockGUI, time);
        verify(mockGUI, times(1)).getNextAction();
        verify(mockMenuController, never()).step(eq(mockGame), eq(GUI.ACTION.NONE), eq(time));
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }

    @Test
    void testStepHandlesNullActionGracefully() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(null);

        menuState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(mockMenuController, never()).step(eq(mockGame), eq(null), eq(time));
        verify(mockMenuViewer, times(1)).draw(mockGUI);
    }

}
