package states;

import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.gui.LanternaGUI;
import com.ldts.crystalclash.states.State;
import com.ldts.crystalclash.viewer.Viewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StateTest {
    private static class TestState extends State<String> {
        public TestState(String model){
            super(model);
        }

        @Override
        protected Viewer<String> getViewer() {
            return mock(Viewer.class);
        }

        @Override
        protected Controller<String> getController() {
            return mock(Controller.class);
        }
    }

    private State<String> state;
    private Game game;
    private LanternaGUI gui;
    private Viewer<String> viewer;
    private Controller<String> controller;


    @BeforeEach
    void setUp() {
        state = new TestState("TestModel");
        game = mock(Game.class);
        gui = mock(LanternaGUI.class);
        viewer = mock(Viewer.class);
        controller = mock(Controller.class);
    }

    @Test
    void testStep() throws Exception {
        // Mock behavior for GUI to return a specific action
        when(gui.getNextAction()).thenReturn(GUI.ACTION.SOME_ACTION); // Replace with a real action

        state.step(game, gui);

        verify(controller).step(game, GUI.ACTION.SOME_ACTION);

        verify(viewer).draw(gui);
    }
}
