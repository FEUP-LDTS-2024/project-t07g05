package states;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.states.State;
import com.ldts.crystalclash.viewer.Viewer;
import java.lang.Object;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    private State<Object> state;
    private Controller<Object> controller;
    private Viewer<Object> viewer;
    private Object model;

    @BeforeEach
    void setUp() {
        model = mock(Object.class);
        controller = mock(Controller.class);
        viewer = mock(Viewer.class);

        state = new State<>(model) {
            @Override
            protected Viewer<Object> getViewer() {
                return viewer;
            }

            @Override
            protected Controller<Object> getController() {
                return controller;
            }
        };
    }

    @Test
    void testGetModel() {
        assertEquals(model, state.getModel());
    }

    @Test
    void testStep() throws IOException {
        Game game = mock(Game.class);
        LanternaGUI gui = mock(LanternaGUI.class);
        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);

        state.step(game, gui);

        verify(gui).getNextAction();
        verify(controller).step(game, GUI.ACTION.NONE);
        verify(viewer).draw(gui);
    }

}
