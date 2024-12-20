package controller;

import com.ldts.crystalclash.controller.TimerController;
import com.ldts.crystalclash.model.Timer;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class TimerControllerTest {

    private TimerController controller;
    private Timer timer;

    @BeforeEach
    void setUp() {
        timer = mock(Timer.class);
        controller = new TimerController(timer);
    }

    @Test
    void testConstructor() {
        assertNotNull(controller, "Controller should be instantiated");
    }

    @Test
    void testStartTimer() {
        controller.startTimer();

        verify(timer).start();
    }

    @Test
    void testStepNoInteraction() throws Exception {
        Game game = mock(Game.class);
        GUI.ACTION action = GUI.ACTION.NONE;

        controller.step(game, action, 0);

        verifyNoInteractions(timer);
    }

    @Test
    void testStepInteraction() throws Exception {
        Game game = mock(Game.class);
        GUI.ACTION action = GUI.ACTION.SELECT;
        controller.step(game, action, 0);

        verifyNoInteractions(timer);
    }
    @Test
    void testStartTimerMultipleCalls() {
        controller.startTimer();
        controller.startTimer();

        verify(timer, times(2)).start();
    }
}