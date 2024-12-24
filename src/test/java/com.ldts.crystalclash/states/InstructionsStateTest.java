package com.ldts.crystalclash.states;

import com.ldts.crystalclash.controller.Controller;
import com.ldts.crystalclash.controller.InstructionsController;
import com.ldts.crystalclash.model.Instructions;
import com.ldts.crystalclash.viewer.InstructionsViewer;
import com.ldts.crystalclash.viewer.Viewer;
import com.ldts.crystalclash.Game;
import com.ldts.crystalclash.gui.GUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InstructionsStateTest {

    private InstructionsState instructionsState;
    private Instructions mockInstructions;
    private Game mockGame;
    private GUI mockGUI;

    @BeforeEach
    public void setUp() {
        mockInstructions = mock(Instructions.class);
        mockGame = mock(Game.class);
        mockGUI = mock(GUI.class);
        instructionsState = new InstructionsState(mockInstructions);
    }

    @AfterEach
    public void tearDown() {
        reset(mockGame, mockGUI, mockInstructions);
    }

    @Test
    public void testGetModel() {
        assertEquals(mockInstructions, instructionsState.getModel());
    }

    @Test
    public void testGetViewer() {
        Viewer<Instructions> viewer = instructionsState.getViewer();
        assertNotNull(viewer, "Viewer should not be null");
        assertInstanceOf(InstructionsViewer.class, viewer, "Viewer should be an instance of InstructionsViewer");
    }

    @Test
    public void testGetController() {
        Controller<Instructions> controller = instructionsState.getController();
        assertNotNull(controller, "Controller should not be null");
        assertInstanceOf(InstructionsController.class, controller, "Controller should be an instance of InstructionsController");
    }

    @Test
    public void testStepCallsControllerAndViewerWithReflection() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.DOWN);

        InstructionsController originalController = new InstructionsController(mockInstructions);
        InstructionsController controllerSpy = spy(originalController);
        InstructionsViewer originalViewer = new InstructionsViewer(mockInstructions);
        InstructionsViewer viewerSpy = spy(originalViewer);

        InstructionsState testState = new InstructionsState(mockInstructions) {
            @Override
            protected Controller<Instructions> getController() {
                return controllerSpy;
            }

            @Override
            protected Viewer<Instructions> getViewer() {
                return viewerSpy;
            }
        };

        testState.step(mockGame, mockGUI, time);

        verify(mockGUI, times(1)).getNextAction();
        verify(controllerSpy, times(1)).step(mockGame, GUI.ACTION.DOWN, time);
        verify(viewerSpy, times(1)).draw(mockGUI);
    }

    @Test
    public void testStepHandlesNullAction() throws Exception {
        long time = 100L;

        when(mockGUI.getNextAction()).thenReturn(GUI.ACTION.NONE);

        InstructionsController controllerSpy = spy(new InstructionsController(mockInstructions));
        InstructionsViewer viewerSpy = spy(new InstructionsViewer(mockInstructions));

        InstructionsState testState = new InstructionsState(mockInstructions) {
            @Override
            protected Controller<Instructions> getController() {
                return controllerSpy;
            }

            @Override
            protected Viewer<Instructions> getViewer() {
                return viewerSpy;
            }
        };

        assertDoesNotThrow(() -> testState.step(mockGame, mockGUI, time));
        verify(controllerSpy, times(1)).step(mockGame, GUI.ACTION.NONE, time); // Assert ACTION.NONE was passed
        verify(viewerSpy, times(1)).draw(mockGUI);
    }

    @Test
    public void testViewerInitialization() {
        InstructionsViewer viewer = new InstructionsViewer(mockInstructions);
        assertNotNull(viewer);
    }

    @Test
    public void testControllerInitialization() {
        InstructionsController controller = new InstructionsController(mockInstructions);
        assertNotNull(controller);
    }
}
