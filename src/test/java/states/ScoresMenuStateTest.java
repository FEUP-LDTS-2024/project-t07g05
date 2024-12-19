package states;

import com.ldts.crystalclash.controller.ScoresMenuController;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.states.ScoresMenuState;
import com.ldts.crystalclash.viewer.ScoresMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ScoresMenuStateTest {

    private TestScoresMenuState scoresMenuState;
    private ScoresMenu scoresMenuMock;

    @BeforeEach
    public void setup() {
        try {
            scoresMenuMock = new ScoresMenu();
        } catch (IOException e) {
            fail("Failed to create ScoresMenu instance due to IOException: " + e.getMessage());
        }
        scoresMenuState = new TestScoresMenuState(scoresMenuMock);
    }

    @Test
    public void testGetViewerReturnsScoresMenuViewer() {
        var viewer = scoresMenuState.exposeGetViewer();

        assertNotNull(viewer, "Viewer should not be null");
        assertInstanceOf(ScoresMenuViewer.class, viewer, "Viewer should be an instance of ScoresMenuViewer");
    }

    @Test
    public void testGetControllerReturnsScoresMenuController() {
        var controller = scoresMenuState.exposeGetController();

        assertNotNull(controller, "Controller should not be null");
        assertInstanceOf(ScoresMenuController.class, controller, "Controller should be an instance of ScoresMenuController");
    }

    @Test
    public void testScoresMenuStateUsesProvidedModel() {
        assertSame(scoresMenuMock, scoresMenuState.getModel(), "ScoresMenuState should use the provided ScoresMenu model");
    }

    private static class TestScoresMenuState extends ScoresMenuState {
        public TestScoresMenuState(ScoresMenu model) {
            super(model);
        }

        public ScoresMenuViewer exposeGetViewer() {
            return (ScoresMenuViewer) super.getViewer();
        }

        public ScoresMenuController exposeGetController() {
            return (ScoresMenuController) super.getController();
        }
    }
}
