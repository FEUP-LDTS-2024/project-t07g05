package viewer;

import com.ldts.crystalclash.gui.GUI;
import com.ldts.crystalclash.model.Tile;
import com.ldts.crystalclash.viewer.TileViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TileViewerTest {
    private TileViewer tileViewer;
    private GUI gui;
    private Tile tile;

    @BeforeEach
    public void setUp() {
        tile = mock(Tile.class);
        gui = mock(GUI.class);
        tileViewer = new TileViewer(tile);
    }

    @Test
    public void testDrawElementsCallsDrawTile() {
        tileViewer.drawElements(gui);

        verify(gui).drawTile(tile);
        verifyNoMoreInteractions(gui);
    }

    @Test
    void drawElementsHandlesException() {
        doThrow(new RuntimeException("GUI error")).when(gui).drawTile(tile);

        assertThrows(RuntimeException.class, () -> tileViewer.drawElements(gui));
    }
}