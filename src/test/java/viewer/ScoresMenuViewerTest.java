package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Position;
import com.ldts.crystalclash.model.ScoreEntry;
import com.ldts.crystalclash.model.ScoresMenu;
import com.ldts.crystalclash.viewer.ScoresMenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ScoresMenuViewerTest {

    private ScoresMenu scoresMenu;
    private GUI gui;
    private TestableScoresMenuViewer testableScoresMenuViewer;

    @BeforeEach
    void setUp() {
        scoresMenu = mock(ScoresMenu.class);
        gui = mock(GUI.class);
        testableScoresMenuViewer = new TestableScoresMenuViewer(scoresMenu);
    }

    @Test
    void drawElementsDisplaysScoresCorrectly() {
        ScoreEntry entry1 = new ScoreEntry(200, "01/01/2024");
        ScoreEntry entry2 = new ScoreEntry(150, "31/12/2023");
        when(scoresMenu.getEntries()).thenReturn(Arrays.asList(entry1, entry2));

        testableScoresMenuViewer.drawElementsPublic(gui);

        verify(gui).drawGameBackground(120, 40);
        verify(gui).drawText(new Position(42, 38), "click 'q' to return to the menu", "#ff3300");
        verify(gui).drawText(new Position(42, 2), "HIGH SCORES", "#FFFFFF");
        verify(gui).drawText(new Position(10, 8), "200", "#FFFFFF");
        verify(gui).drawText(new Position(20, 8), "01/01/2024", "#FFFFFF");
        verify(gui).drawText(new Position(10, 10), "150", "#FFFFFF");
        verify(gui).drawText(new Position(20, 10), "31/12/2023", "#FFFFFF");
        verifyNoMoreInteractions(gui);
    }

    @Test
    void drawElementsDisplaysNoScoresMessageWhenEmpty() {
        when(scoresMenu.getEntries()).thenReturn(Collections.emptyList());

        testableScoresMenuViewer.drawElementsPublic(gui);

        verify(gui).drawGameBackground(120, 40);
        verify(gui).drawText(new Position(42, 38), "click 'q' to return to the menu", "#ff3300");
        verify(gui).drawText(new Position(42, 2), "HIGH SCORES", "#FFFFFF");
        verify(gui).drawText(new Position(40, 20), "NO SCORES AVAILABLE", "#FFFFFF");
        verifyNoMoreInteractions(gui);
    }

    @Test
    void drawElementsHandlesException() {
        when(scoresMenu.getEntries()).thenThrow(new RuntimeException("Error fetching scores"));

        assertThrows(RuntimeException.class, () -> testableScoresMenuViewer.drawElementsPublic(gui));
    }

    // Subclass to expose the protected method
    private static class TestableScoresMenuViewer extends ScoresMenuViewer {
        public TestableScoresMenuViewer(ScoresMenu model) {
            super(model);
        }

        public void drawElementsPublic(GUI gui) {
            super.drawElements(gui);
        }
    }
}
